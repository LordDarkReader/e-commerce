package com.czak.ecommerce.product;

import jakarta.validation.constraints.NotNull;

public record ProductPurchaseRequest(
        @NotNull(message = "ProductId is required")
        Integer productId,
        @NotNull(message = "Quantity is required")
        double quantity
) {
}
