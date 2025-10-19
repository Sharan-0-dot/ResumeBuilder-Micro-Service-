package com.Sharan.AI_Service.Model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Resume {
    @Id
    private ObjectId id;

    @Valid
    @NotNull(message = "personal Information cannot be null")
    private PersonalInformation personalInformation;
    private Education[] education;
    private Skills[] skills;
    private Project[] projects;
    private Experience[] experiences;
    private Certification[] certifications;
    private String[] achievements;
    private String summary;
    private String[] additional;
}
