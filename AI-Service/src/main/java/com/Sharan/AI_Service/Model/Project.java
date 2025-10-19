package com.Sharan.AI_Service.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    private String title;
    private String[] techStack;
    private String description;
    private String[] features;
    private String gitHubLink;
    private String liveLink;
}
