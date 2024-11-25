package com.czak.ecommerce.order;

import com.czak.ecommerce.customer.CustomerClient;
import com.czak.ecommerce.exception.BusinessException;
import com.czak.ecommerce.kafka.OrderConfirmation;
import com.czak.ecommerce.kafka.OrderProducer;
import com.czak.ecommerce.orderline.OrderLineRequest;
import com.czak.ecommerce.orderline.OrderLineService;
import com.czak.ecommerce.payment.PaymentClient;
import com.czak.ecommerce.payment.PaymentRequest;
import com.czak.ecommerce.product.ProductClient;
import com.czak.ecommerce.product.PurchaseRequest;
import com.czak.ecommerce.product.PurchaseResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository orderRepository;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;
    public Integer createOrder(OrderRequest request) {
        var customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow( () -> new BusinessException("Cannot create order:: No customer exists with the provided ID"));

        var purchaseProducts = this.productClient.purchaseProducts(request.products());

        var order = this.orderRepository.save(mapper.toOrder(request));

        for (PurchaseRequest purchaseRequest: request.products()) {
            orderLineService.saveOrderLine(new OrderLineRequest(null, order.getId(), purchaseRequest.productId(), purchaseRequest.quantity()));
        }

        var paymentRequest = new PaymentRequest(request.amount(), request.paymentMethod(), order.getId(), order.getReference(), customer);
        paymentClient.requestOrderPayment(paymentRequest);

        orderProducer.sendOrderConfirmation(new OrderConfirmation(request.reference(), request.amount(), request.paymentMethod(), customer, purchaseProducts));

        return order.getId();
    }

    public List<OrderResponse> findAll() {
        return orderRepository.findAll().stream().map(mapper::fromOrder).collect(Collectors.toList());
    }

    public OrderResponse findById(Integer orderId) {
        return orderRepository.findById(orderId).map(mapper::fromOrder).orElseThrow(() -> new EntityNotFoundException(String.format("No order found with the provided ID: %d", orderId)));
    }
}
