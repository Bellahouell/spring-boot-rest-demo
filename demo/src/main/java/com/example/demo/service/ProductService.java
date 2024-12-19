package com.example.demo.service;

import com.example.demo.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ProductService {
    private final List<Product> products = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong();

    public ProductService() {
        // Add some sample data
        products.add(new Product(counter.incrementAndGet(), "Laptop", 999.99, "High-performance laptop"));
        products.add(new Product(counter.incrementAndGet(), "Smartphone", 599.99, "Latest smartphone"));
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public Optional<Product> getProductById(Long id) {
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst();
    }

    public Product createProduct(Product product) {
        product.setId(counter.incrementAndGet());
        products.add(product);
        return product;
    }

    public Optional<Product> updateProduct(Long id, Product updatedProduct) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(id)) {
                updatedProduct.setId(id);
                products.set(i, updatedProduct);
                return Optional.of(updatedProduct);
            }
        }
        return Optional.empty();
    }

    public boolean deleteProduct(Long id) {
        return products.removeIf(product -> product.getId().equals(id));
    }
}