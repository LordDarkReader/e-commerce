package com.czak.ecommerce.product;

import jakarta.validation.Valid;
import jakarta.ws.rs.QueryParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/add-product")
    public ResponseEntity<Integer> createProduct(@ModelAttribute ProductRequest request) throws IOException {
        return ResponseEntity.ok(productService.createProduct(request));
    }

    @PostMapping("/upload")
    public ResponseEntity<Integer> uploadImages(@RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok(productService.createProduct2(file));
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> purchaseProduct(@RequestBody @Valid List<ProductPurchaseRequest> request) {
        return ResponseEntity.ok(productService.purchaseProduct(request));
    }
    @GetMapping("/{product-id}")
    public ResponseEntity<ProductResponse> findById(@PathVariable("product-id") Integer productId) {
        return ResponseEntity.ok(productService.findById(productId));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductResponse>> findByText(@QueryParam("text") String text) {
        return ResponseEntity.ok(productService.findByText(text));
    }

    @GetMapping()
    public ResponseEntity<List<ProductResponse>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }
}
