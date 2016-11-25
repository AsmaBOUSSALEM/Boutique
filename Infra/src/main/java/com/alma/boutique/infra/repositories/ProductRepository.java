package com.alma.boutique.infra.repositories;

import model.product.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by asmaboussalem on 16/11/2016.
 */
public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findByName(@Param("name") String name);
}
