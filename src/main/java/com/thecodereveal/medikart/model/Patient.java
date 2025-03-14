package com.thecodereveal.medikart.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "patients")
public class Patient {

    @Id
    private String id;
    private String name;
    private String phoneNumber;
    private String email;
    private int age;
    @DBRef
    private User user;
}
