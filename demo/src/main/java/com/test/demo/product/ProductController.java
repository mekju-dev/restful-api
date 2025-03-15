package com.test.demo.product;

import com.test.demo.product.model.Product;
import com.test.demo.product.model.ProductDTO;
import com.test.demo.product.model.UpdateProductCommand;
import com.test.demo.product.services.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private final CreateProductService createProductService;
    private final DeleteProductService deleteProductService;
    private final GetProductService getProductService;
    private final GetProductsService getProductsService;
    private final UpdateProductService updateProductService;

    public ProductController(CreateProductService createProductService, UpdateProductService updateProductService, GetProductsService getProductsService, DeleteProductService deleteProductService, GetProductService getProductService) {
        this.createProductService = createProductService;
        this.updateProductService = updateProductService;
        this.getProductsService = getProductsService;
        this.deleteProductService = deleteProductService;
        this.getProductService = getProductService;
    }



    @PostMapping("/product")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody Product product) {
        return createProductService.execute(product);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return updateProductService.execute(new UpdateProductCommand(id, product));
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        return getProductService.execute(id);
    }
    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getProducts(){
        return getProductsService.execute(null);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        return deleteProductService.execute(id);
    }
}
