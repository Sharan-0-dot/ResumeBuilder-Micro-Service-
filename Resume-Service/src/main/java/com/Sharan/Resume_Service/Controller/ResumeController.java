package com.Sharan.Resume_Service.Controller;

import com.Sharan.Resume_Service.Model.Resume;
import com.Sharan.Resume_Service.Service.ResumeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/resume")
@Slf4j
public class ResumeController {

    private final ResumeService service;

    @PostMapping("/")
    public ResponseEntity<?> postResume(@Valid @RequestBody Resume resume) {
        try {
            byte[] res = service.postResume(resume);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", "MyResume.docx");
            return new ResponseEntity<>(res, headers, HttpStatus.OK);
        } catch (RuntimeException e) {
            log.error(e.getLocalizedMessage());
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

}
