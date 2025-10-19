package com.Sharan.AI_Service.Controller;

import com.Sharan.AI_Service.Model.Resume;
import com.Sharan.AI_Service.Service.AIResumeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class AIResumeController {

    private final AIResumeService service;

    @PostMapping("/getAiResume")
    public ResponseEntity<?> getResponse(@Valid @RequestBody Resume resume) {
        return new ResponseEntity<>(service.getResponse(resume), HttpStatus.OK);
    }
}
