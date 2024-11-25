package com.czak.ecommerce.kafka;

import com.czak.ecommerce.customer.CustomerResponse;
import com.czak.ecommerce.order.PaymentMethod;
import com.czak.ecommerce.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(String orderReference,
                                BigDecimal totalAmount,
                                PaymentMethod paymentMethod,
                                CustomerResponse customer,
                                List<PurchaseResponse> products) {
}
