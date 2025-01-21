package com.czak.ecommerce.Controller;

import com.czak.ecommerce.model.ProductDescRequest;
import com.czak.ecommerce.product.ProductResponse;
import com.czak.ecommerce.product.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProductControllerImpl implements ProductController {
    private final ProductService productService;

    @Override
    public ResponseEntity<List<ProductResponse>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    @Override
    public ResponseEntity<List<ProductResponse>> findByText(String text) {
        return ResponseEntity.ok(productService.findByText(text));
    }

    @Override
    public ResponseEntity<String> uploadImages(MultipartFile file) throws IOException {
        return ResponseEntity.ok(productService.uploadImages(file));
    }


    @Override
    public ResponseEntity<String> uploadFileAndDescription(MultipartFile file, String descJson) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ProductDescRequest desc = objectMapper.readValue(descJson, ProductDescRequest.class);

            String originalFilename = file.getOriginalFilename();
            long fileSize = file.getSize();
            System.out.println("Received file: " + originalFilename + " (" + fileSize + " bytes)");

            System.out.println("Received description: " + desc);

            return ResponseEntity.ok(productService.uploadImages(file));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request: " + e.getMessage());
        }
    }
}
