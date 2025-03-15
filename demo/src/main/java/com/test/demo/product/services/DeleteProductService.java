package com.test.demo.product.services;

import com.test.demo.Command;
import com.test.demo.exceptions.ErrorMessages;
import com.test.demo.exceptions.ProductNotFoundException;
import com.test.demo.product.ProductRepository;
import com.test.demo.product.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteProductService implements Command<Long, Void> {

    private final ProductRepository productRepository;
    public DeleteProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<Void> execute(Long input) {
        Optional<Product> productOptional = productRepository.findById(input);
        if (productOptional.isPresent()) {
            productRepository.deleteById(input);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        throw new ProductNotFoundException();
    }
}
