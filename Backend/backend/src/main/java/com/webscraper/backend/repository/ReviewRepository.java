package com.webscraper.backend.repository;

import com.webscraper.backend.model.Review;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReviewRepository extends MongoRepository<Review,String> {
}
