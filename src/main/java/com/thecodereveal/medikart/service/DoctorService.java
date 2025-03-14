package com.thecodereveal.medikart.service;

import com.thecodereveal.medikart.dto.DoctorDto;
import com.thecodereveal.medikart.model.Doctor;
import com.thecodereveal.medikart.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public List<DoctorDto> getAllDoctors(){
        List<Doctor> doctors = doctorRepository.findAll();
        return doctors.stream().map(doctor -> DoctorDto.builder()
               .email(doctor.getEmail())
               .name(doctor.getName())
               .id(doctor.getId())
               .phone(doctor.getPhone())
               .availableSlots(doctor.getAvailableSlots())
               .image(doctor.getImage())
               .fee(doctor.getConsultationFee())
               .user(doctor.getUser().getId())
               .rating(doctor.getRating())
               .speciality(doctor.getSpeciality().getId())
               .build()).collect(Collectors.toList());
    }

    public Doctor createDoctor(Doctor doctor){
        return doctorRepository.save(doctor);
    }

    public DoctorDto getDoctorById(String doctorId){
        return doctorRepository.findById(doctorId).map(doctor -> DoctorDto.builder()
                        .email(doctor.getEmail())
                        .name(doctor.getName())
                        .id(doctor.getId())
                        .phone(doctor.getPhone())
                        .availableSlots(doctor.getAvailableSlots())
                        .image(doctor.getImage())
                        .fee(doctor.getConsultationFee())
                        .user(doctor.getUser().getId())
                        .rating(doctor.getRating())
                        .speciality(doctor.getSpeciality().getId()).build())
                .orElseThrow(()-> new RuntimeException("Doctor Not Found !"));
    }


}
