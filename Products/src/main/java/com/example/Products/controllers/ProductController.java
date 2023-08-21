package com.example.Products.controllers;

import com.example.Products.models.ProductsResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;

import static com.example.Products.services.FileService.formatTitle;
import static com.example.Products.services.FileService.writeToFile;

@RestController
public class ProductController {
    @GetMapping("/getProducts")
    public String getProductsFromStoreApi() {
        String apiUrl = "https://fakestoreapi.com/products";
        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<ProductsResponse[]> response = restTemplate.getForEntity(apiUrl, ProductsResponse[].class);
            ProductsResponse[] products = response.getBody();

            if (products != null) {
                StringBuilder finalTextBuilder = new StringBuilder();
                finalTextBuilder.append(String.format("%-5s | %-40s | %-10s%n", "ID", "Title", "Price"));
                finalTextBuilder.append(String.join("", Collections.nCopies(70, "-"))).append(System.lineSeparator());

                for (ProductsResponse product : products) {
                    finalTextBuilder.append(String.format("%-5d | %-50s | %-10.2f%n", product.getId(), formatTitle(product.getTitle()), product.getPrice()));
                }

                writeToFile("FirstFileCreated.txt", finalTextBuilder.toString());

                return "Products fetched and saved.";
            } else {
                return "No products found.";
            }
        } catch (RestClientException | IOException e) {
            return "Error writing to file: " + e.getMessage();
        }
    }
}
