package com.thecodereveal.medikart.dto;

import com.thecodereveal.medikart.model.TimeSlot;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorDto {
    private String id;
    private String name;
    private String email;
    private String speciality;
    private String phone;
    private String user;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String image;
    private float rating;
    private int fee;
    private List<TimeSlot> availableSlots;
}
