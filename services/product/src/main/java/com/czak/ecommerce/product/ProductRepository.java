package com.czak.ecommerce.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByIdInOrderById(List<Integer> productIds);

    @Query("select p from Product p where p.name like %?1%")
    List<Product> findAllByText(String text);
}
