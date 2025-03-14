package com.thecodereveal.medikart.repository;

import com.thecodereveal.medikart.model.Appointment;
import com.thecodereveal.medikart.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentsRepository extends MongoRepository<Appointment,String> {

    List<Appointment> findByUser(User user);
}
