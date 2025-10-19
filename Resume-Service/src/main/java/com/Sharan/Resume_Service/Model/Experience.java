package com.Sharan.Resume_Service.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Experience {
    private String role;
    private String company;
    private String location;
    private LocalDate startDate;
    private LocalDate endDate;
    private String[] responsibilities;
    private String techUsed;
}
