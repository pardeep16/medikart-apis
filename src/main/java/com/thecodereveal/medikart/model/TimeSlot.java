package com.thecodereveal.medikart.model;

import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Data
public class TimeSlot {

    private String id;
    private DayOfWeek dayOfWeek;
    private String startTime;
    private String endTime;
    private boolean available;
}
