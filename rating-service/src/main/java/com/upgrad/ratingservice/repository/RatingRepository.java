package com.upgrad.ratingservice.repository;

import com.upgrad.ratingservice.model.DoctorRating;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RatingRepository extends MongoRepository<DoctorRating, String> {
    public DoctorRating findByDoctorId(String id);
}
