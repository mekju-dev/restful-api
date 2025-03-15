package com.test.demo.product.services;

import com.test.demo.Query;
import com.test.demo.exceptions.ProductNotFoundException;
import com.test.demo.product.ProductRepository;
import com.test.demo.product.model.Product;
import com.test.demo.product.model.ProductDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetProductService implements Query<Long, ProductDTO> {
    private final ProductRepository productRepository;
    public GetProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<ProductDTO> execute(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return ResponseEntity.ok(new ProductDTO(product.get()));
        }
        throw new ProductNotFoundException();
    }
}
