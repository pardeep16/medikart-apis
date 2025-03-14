package com.thecodereveal.medikart.model;

import com.mongodb.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "doctors")
public class Doctor {

    @Id
    private String id;
    private String name;
    private String email;
    @DBRef
    @NonNull
    private Speciality speciality;
    private String phone;
    @DBRef
    @NonNull
    private User user;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String image;
    private float rating;
    private int consultationFee;
    private List<TimeSlot> availableSlots;
}
