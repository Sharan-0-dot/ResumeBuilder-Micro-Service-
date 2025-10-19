package com.Sharan.AI_Service.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Certification {
    private String title;
    private String issuer;
    private LocalDate date;
    private String link;
}
