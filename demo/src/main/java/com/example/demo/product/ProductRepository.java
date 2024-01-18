package com.example.demo.product;

import com.example.demo.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductRepository {
    private Map<Integer, Product> db = new HashMap<>();
    private int id = 1;

    public Product findProduct(int id) {
        return db.get(id);
    }

    public void save(Product product) {
        db.put(id++, product);
    }

    public List<Product> productsList() {
        return new ArrayList<>(db.values());
    }
}
