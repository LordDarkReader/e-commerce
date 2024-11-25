package com.czak.payment.notification;

import com.czak.payment.payment.PaymentMethod;

import java.math.BigDecimal;

public record PaymentNotificationRequest(String orderReference, BigDecimal amount, PaymentMethod paymentMethod, String customerFirstName, String customerLastName, String customerEmail) {
}
