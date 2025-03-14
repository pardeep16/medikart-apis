package com.thecodereveal.medikart.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentDto {
    private String id;
    private String doctor;
    private String user;
    private String status;
    private Map<String,String> patient;
    private Map<String,String> slot;
}
