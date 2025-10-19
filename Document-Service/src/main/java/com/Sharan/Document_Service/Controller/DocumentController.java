package com.Sharan.Document_Service.Controller;

import com.Sharan.Document_Service.Model.ResumeDTO;
import com.Sharan.Document_Service.Service.DocumentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/document/download")
public class DocumentController {

    private final DocumentService service;

    @PostMapping("/")
    public ResponseEntity<?> downloadDocument(@RequestBody ResumeDTO resume) {
        try {
            byte[] res = service.download(resume.getText());
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error creating document");
            return new ResponseEntity<>("Document creation error", HttpStatus.BAD_REQUEST);
        }
    }
}
