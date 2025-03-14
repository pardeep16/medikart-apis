package com.thecodereveal.medikart.service;

import com.thecodereveal.medikart.dto.AppointmentDto;
import com.thecodereveal.medikart.model.Appointment;
import com.thecodereveal.medikart.model.Patient;
import com.thecodereveal.medikart.model.User;
import com.thecodereveal.medikart.repository.AppointmentsRepository;
import com.thecodereveal.medikart.repository.DoctorRepository;
import com.thecodereveal.medikart.repository.PatientRepository;
import com.thecodereveal.medikart.repository.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private UserDetailRepository userDetailRepository;

    @Autowired
    private AppointmentsRepository appointmentsRepository;

    public AppointmentDto createAppointment(AppointmentDto appointmentDto){
        Map<String,String> slot = appointmentDto.getSlot();
        Map<String,String> patient = appointmentDto.getPatient();
        Patient patient1= Patient.builder().email(patient.getOrDefault("email",""))
                .phoneNumber(patient.getOrDefault("phoneNumber",""))
                .name(patient.get("name"))
                .age(Integer.parseInt(patient.get("age"))).build();
        // Logic to check if patient is already exist

        Appointment appointment = appointmentsRepository.save(Appointment.builder()
                .patient(patientRepository.save(patient1))
                .date(slot.get("date"))
                .user(userDetailRepository.findById(appointmentDto.getUser()).get())
                .doctor(doctorRepository.findById(appointmentDto.getDoctor()).get())
                .status(Appointment.AppointmentStatus.SCHEDULED)
                .startTime(slot.get("time")).build());
        appointmentDto.setId(appointment.getId());
        return appointmentDto;
    }

    public List<AppointmentDto> getAppointments(Principal principal){
        String userName = principal.getName();
        User user = userDetailRepository.findByEmail(userName);
        List<Appointment> list = appointmentsRepository.findByUser(user);
        return  list.stream().map(appointment -> {
            Map<String,String> slot = new HashMap<>();
            slot.put("time",appointment.getStartTime());
            slot.put("date",appointment.getDate());
            Map<String, String> map = new HashMap<>();
            map.put("id", appointment.getPatient().getId());
            map.put("name", appointment.getPatient().getName());
            map.put("phoneNumber", appointment.getPatient().getPhoneNumber());
            map.put("email", appointment.getPatient().getEmail());
            map.put("age", String.valueOf(appointment.getPatient().getAge()));
            return AppointmentDto.builder()
                    .id(appointment.getId())
                    .status(String.valueOf(appointment.getStatus()))
                    .patient(map)
                    .slot(slot)
                    .user(appointment.getUser().getId())
                    .doctor(appointment.getDoctor().getId()).build();
        }).collect(Collectors.toList());
    }
}
