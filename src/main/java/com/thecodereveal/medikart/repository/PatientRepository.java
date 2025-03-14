package com.thecodereveal.medikart.repository;

import com.thecodereveal.medikart.model.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends MongoRepository<Patient,String> {


}
