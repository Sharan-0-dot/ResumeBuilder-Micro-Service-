package com.ResumeBuilder.Gateway.Model;


import com.mongodb.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private ObjectId objectId;
    @Indexed(unique = true)
    @NonNull
    private String username;
    @NonNull
    private String password;
    private List<String> roles;
}
