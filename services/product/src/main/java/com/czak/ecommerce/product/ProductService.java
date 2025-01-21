package com.czak.ecommerce.product;

import com.czak.ecommerce.category.Category;
import com.czak.ecommerce.exception.ProductPurchaseException;
import com.czak.ecommerce.service.StorageService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    private final StorageService storageService;

    public Integer createProduct(ProductRequest request) throws IOException {
        var product = productMapper.toProduct(request);
        return productRepository.save(product).getId();
    }

    public Integer createProduct2(MultipartFile file) throws IOException {
        var product = new Product.ProductBuilder()
                .name("sadsads")
                .image(file.getBytes())
                .price(new BigDecimal(2))
                .category(Category.builder()
                        .id(1)
                        .description("fdfsd")
                        .products(null)
                        .build())
                .availableQuantity(1)
                .description("dasdsad")
                .build();
       // var product = productMapper.toProduct(request);
        return productRepository.save(product).getId();
    }

    public List<ProductPurchaseResponse> purchaseProduct(List<ProductPurchaseRequest> request) {
        var productIds = request
                .stream()
                .map(ProductPurchaseRequest::productId)
                .toList();
        var storedProducts = productRepository.findAllByIdInOrderById(productIds);
        if (productIds.size() != storedProducts.size()) {
            throw new ProductPurchaseException("One or more products does not exists");
        }
        var storedRequest = request
                .stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();
        var purchaseProducts = new ArrayList<ProductPurchaseResponse>();

        for (int i = 0; i < storedProducts.size(); i++) {
            var product = storedProducts.get(i);
            var productRequest = storedRequest.get(i);
            if (product.getAvailableQuantity() < productRequest.quantity()) {
                throw new ProductPurchaseException("Insufficient stock quantity for product with ID:: " + productRequest.productId());
            }
            var newAvailableQuantity = product.getAvailableQuantity() - productRequest.quantity();
            product.setAvailableQuantity(newAvailableQuantity);
            productRepository.save(product);
            purchaseProducts.add(productMapper.toProductPurchaseResponse(product, productRequest.quantity()));
        }
        return purchaseProducts;
    }

    public ProductResponse findById(Integer productId) {
        return productRepository.findById(productId)
                .map(productMapper::toProductResponse)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with the ID:: " + productId));
    }

    public List<ProductResponse> findByText(String text) {
        return productRepository.findAllByText(text).stream().map(productMapper::toProductResponse).collect(Collectors.toList());
    }

    public List<ProductResponse> findAll() {
        return productRepository.findAll().stream().map(productMapper::toProductResponse).collect(Collectors.toList());
    }

    public String uploadImages(MultipartFile file) throws IOException {
        return storageService.uploadFile(file.getOriginalFilename(), file.getInputStream(), MediaType.MULTIPART_FORM_DATA_VALUE);
    }
}
