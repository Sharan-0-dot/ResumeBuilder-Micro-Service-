package com.Sharan.AI_Service.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Education {
    private String degree;
    private String branch;
    private String institute;
    private String location;
    private LocalDate startDate;
    private LocalDate endDate;
    private String cgpa;
}
