package com.Sharan.AI_Service.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Skills {
    private String[] languages;
    private String[] frameworks;
    private String[] databases;
    private String[] tools;
    private String[] concepts;
}
