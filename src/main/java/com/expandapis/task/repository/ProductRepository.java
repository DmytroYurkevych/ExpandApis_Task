package com.expandapis.task.repository;

import com.expandapis.task.entity.Product;
import java.util.List;

public interface ProductRepository extends org.springframework.data.repository.Repository<Product, Long> {
    void saveAll(String table, List<Product> products);
    List<Product> findAll(String table);
}
