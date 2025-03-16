package com.test.demo.product;

import com.test.demo.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    String getProductsById(Long id);

    //spring data jpa
    List<Product> findByNameContaining(String name);
}
