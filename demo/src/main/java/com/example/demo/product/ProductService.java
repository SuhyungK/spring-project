package com.example.demo.product;

import com.example.demo.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product findProduct(int id) {
        return productRepository.findProduct(id);
    }

    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public List<Product> productList() {
        return productRepository.productsList();
    }
}
