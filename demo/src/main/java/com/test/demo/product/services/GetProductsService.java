package com.test.demo.product.services;

import com.test.demo.Query;
import com.test.demo.product.ProductRepository;
import com.test.demo.product.model.Product;
import com.test.demo.product.model.ProductDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetProductsService implements Query<Void, List<ProductDTO>> {

    private final ProductRepository productRepository;
    public GetProductsService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @Override
    public ResponseEntity<List<ProductDTO>> execute(Void input) {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOS = products.stream().map(ProductDTO::new).toList(); //tricky
        //Look into stream map and collections methods
        return ResponseEntity.status(HttpStatus.OK).body(productDTOS);
    }
}
