package com.thecodereveal.medikart.repository;

import com.thecodereveal.medikart.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailRepository extends MongoRepository<User,String> {

    User findByEmail(String email);
}
