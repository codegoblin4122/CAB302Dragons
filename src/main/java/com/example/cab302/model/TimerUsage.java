package com.example.cab302.model;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;

public class TimerUsage {
    private int id;
    private String userEmail;
    private Timestamp startTime;
    private Timestamp endTime;

    public TimerUsage(int id, String userEmail, Timestamp startTime, Timestamp endTime) {
        this.id = id;
        this.userEmail = userEmail;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getId() {
        return id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }
    public Duration getDuration() {
        LocalDateTime start = startTime.toLocalDateTime();
        LocalDateTime end = endTime.toLocalDateTime();
        return Duration.between(start, end);
    }
}
