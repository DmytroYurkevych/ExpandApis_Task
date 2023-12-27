package com.expandapis.task.repository;

import com.expandapis.task.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class ProductRepositoryImpl implements ProductRepository {


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveAll(String table, List<Product> products) {
        entityManager.createNativeQuery(
                "CREATE TABLE IF NOT EXISTS " + table +
                " (entry_date TIMESTAMP(6), " +
                "id BIGINT AUTO_INCREMENT NOT NULL, " +
                "item_code BIGINT, " +
                "item_quantity BIGINT, " +
                "item_name VARCHAR(255), " +
                "status VARCHAR(255), " +
                "PRIMARY KEY (id))"
        ).executeUpdate();
    }

    @Override
    public List<Product> findAll(String table) {
        return entityManager.createQuery("SELECT p from" + table, Product.class).getResultList();
    }
}
