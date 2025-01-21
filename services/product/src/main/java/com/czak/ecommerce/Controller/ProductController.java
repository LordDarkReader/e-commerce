package com.czak.ecommerce.Controller;

import com.czak.ecommerce.product.ProductResponse;
import jakarta.ws.rs.QueryParam;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequestMapping("/api/v1/products/new")
public interface ProductController {

    @GetMapping()
    @ResponseBody
    public ResponseEntity<List<ProductResponse>> findAll();

    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<ProductResponse>> findByText(@QueryParam("text") String text);

    @PostMapping("/upload")
    //@ResponseBody
    public ResponseEntity<String> uploadImages(@RequestParam("file") MultipartFile file) throws IOException;

    @PostMapping("/upload-desc")
    //@ResponseBody
    public ResponseEntity<String> uploadFileAndDescription(@RequestParam("file") MultipartFile file, @RequestParam("desc") String descJson) throws IOException;

}
