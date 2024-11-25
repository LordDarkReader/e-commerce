package com.czak.notification.kafka.order;

import com.czak.notification.kafka.payment.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(String orderReference, BigDecimal amount, PaymentMethod paymentMethod, Customer customer, List<Product> products) {
}
