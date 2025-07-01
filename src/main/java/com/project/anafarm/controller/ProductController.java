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

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }


    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @PostMapping("/upload")
    public String uploadImage(@RequestParam("image") MultipartFile imageFile) throws IOException {
        // Caminho relativo à pasta static
        String folderPath = "src/main/resources/static/";
        Files.createDirectories(Paths.get(folderPath)); // cria a pasta se não existir

        // Gera nome único
        String filename = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();

        // Caminho completo do arquivo
        Path filePath = Paths.get(folderPath + filename);

        // Salva o arquivo no disco
        Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        // Retorna a URL relativa para ser usada no <img src="">
        return "/" + filename;
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