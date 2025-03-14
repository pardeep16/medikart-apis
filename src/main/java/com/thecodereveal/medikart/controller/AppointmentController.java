package com.thecodereveal.medikart.controller;

import com.thecodereveal.medikart.dto.AppointmentDto;
import com.thecodereveal.medikart.model.Appointment;
import com.thecodereveal.medikart.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping
    public AppointmentDto createAppointment(@RequestBody AppointmentDto appointmentDto){
        return appointmentService.createAppointment(appointmentDto);
    }

    @GetMapping
    public List<AppointmentDto> getAppointments(Principal principal){
        return appointmentService.getAppointments(principal);
    }
}
