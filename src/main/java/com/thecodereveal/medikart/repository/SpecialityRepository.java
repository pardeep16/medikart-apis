package com.thecodereveal.medikart.repository;

import com.thecodereveal.medikart.model.Speciality;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialityRepository extends MongoRepository<Speciality,String> {
}
