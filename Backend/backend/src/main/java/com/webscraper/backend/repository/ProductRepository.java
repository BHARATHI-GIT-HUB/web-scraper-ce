package com.webscraper.backend.repository;

import com.webscraper.backend.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product,String> {



}
