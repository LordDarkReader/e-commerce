package com.czak.ecommerce.customer;



import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

public record CustomerRequest(String id,
                              @NotNull(message = "Customer firstname is required")
                              String firstname,
                              @NotNull(message = "Customer lastname is required")
                              String lastname,
                              @NotNull(message = "Customer email is required")
                              @Email(message = "Customer email is not a valid email address")
                              String email,
                              @Validated
                              Address address) {
}
