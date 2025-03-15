package com.test.demo.product.services;

import com.test.demo.Command;
import com.test.demo.exceptions.ErrorMessages;
import com.test.demo.exceptions.ProductNotValidException;
import com.test.demo.product.ProductRepository;
import com.test.demo.product.model.Product;
import com.test.demo.product.model.ProductDTO;
import io.micrometer.common.util.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateProductService implements Command<Product, ProductDTO> {

    private final ProductRepository productRepository;
    public CreateProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<ProductDTO> execute(Product product) {

        validateProduct(product);

        Product savedProduct = productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ProductDTO(savedProduct));
    }

    private static void validateProduct(Product product) {
        if(StringUtils.isEmpty(product.getName())) {
            throw new ProductNotValidException(ErrorMessages.PRODUCT_NAME_NOT_VALID.getMessage());
        }

        if(product.getDescription().length() < 20 || product.getDescription().length() > 100) {
            throw new ProductNotValidException(ErrorMessages.PRODUCT_DESCRIPTION_NOT_VALID.getMessage());
        }

        if(product.getPrice() == null || product.getPrice() < 0) {
            throw new ProductNotValidException(ErrorMessages.PRODUCT_PRICE_NOT_VALID.getMessage());
        }
    }
}
