package com.thecodereveal.medikart.repository;

import com.thecodereveal.medikart.model.Doctor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository  extends MongoRepository<Doctor,String> {

}
