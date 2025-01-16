package com.czak.ecommerce.product;

import com.czak.ecommerce.category.Category;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ProductMapper {
    public Product toProduct(ProductRequest request) throws IOException {
        return Product.builder()
                .id(request.id())
                .name(request.name())
                .description(request.description())
               // .availableQuantity(request.availableQuantity())
                .category(Category.builder().id(request.categoryId()).build())
                .price(request.price())
                .image(request.image().getBytes())
                .build();
    }

    public ProductResponse toProductResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getAvailableQuantity(),
                product.getPrice(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getCategory().getDescription());
    }

    public ProductPurchaseResponse toProductPurchaseResponse(Product product, double quantity) {
        return new ProductPurchaseResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                quantity
        );
    }
}
