package com.thecodereveal.medikart.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "appointments")
public class Appointment {

    @Id
    private String id;
    private String startTime;
    private String endTime;
    private String date;
    private AppointmentStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @DBRef
    @JsonIgnore
    private User user;
    @DBRef
    private Patient patient;
    @DBRef
    private Doctor doctor;


    public enum AppointmentStatus{
        SCHEDULED,
        CANCELLED,
        COMPLETED,
        CONFIRMED
    }
}
