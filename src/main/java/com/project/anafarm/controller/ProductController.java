package com.project.anafarm.controller;

import com.project.anafarm.model.Product;
import com.project.anafarm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.*;
import java.util.UUID;
import java.io.IOException;




import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @PostMapping("/upload")
    public String uploadImage(@RequestParam("image") MultipartFile imageFile) throws IOException {
    String folder = "src/main/resources/static/images/";
    String filename = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();

    Path path = Paths.get(folder + filename);
    Files.copy(imageFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

    return "/images/" + filename;
}

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        return productService.updateProduct(id, updatedProduct);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "Produto deletado!";
    }
}