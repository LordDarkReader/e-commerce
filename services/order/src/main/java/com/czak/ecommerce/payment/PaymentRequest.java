package com.czak.ecommerce.payment;

import com.czak.ecommerce.customer.CustomerResponse;
import com.czak.ecommerce.order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(BigDecimal amount, PaymentMethod paymentMethod, Integer orderId, String orderReference, CustomerResponse customer) {
}
