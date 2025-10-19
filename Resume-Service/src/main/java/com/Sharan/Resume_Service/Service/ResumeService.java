package com.Sharan.Resume_Service.Service;

import com.Sharan.Resume_Service.Model.Resume;
import com.Sharan.Resume_Service.Model.ResumeDTO;
import com.Sharan.Resume_Service.Repository.ResumeRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service
@Slf4j
public class ResumeService {

    private final ResumeRepo repo;
    private final WebClient aiServiceWebClient;
    private final WebClient documentServiceClient;

    public ResumeService(
            ResumeRepo repo,
            @Qualifier("aiServiceWebClient") WebClient aiServiceWebClient,
            @Qualifier("downloadService") WebClient documentServiceClient
    ) {
        this.repo = repo;
        this.aiServiceWebClient = aiServiceWebClient;
        this.documentServiceClient = documentServiceClient;
    }

    public byte[] postResume(Resume resume) {
        try {
            Resume savedResume = repo.save(resume);
            if(savedResume == null) throw new RuntimeException("Error Saving resume Request");
            String response = getResume(savedResume);
            ResumeDTO dto = new ResumeDTO();
            dto.setText(response);
            return downloadResume(dto);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public String getResume(Resume resume) {
        try {
            return aiServiceWebClient.post()
                    .uri("/api/ai/getAiResume")
                    .bodyValue(resume)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
        } catch (WebClientResponseException e) {
            log.error("AI service error: {} {}", e.getStatusCode().value(), e.getResponseBodyAsString());
            throw new RuntimeException(e);
        }
    }

    public byte[] downloadResume(ResumeDTO dto) {
        try {
            return documentServiceClient.post()
                    .uri("/api/document/download/")
                    .bodyValue(dto)
                    .retrieve()
                    .bodyToMono(byte[].class)
                    .block();
        } catch (WebClientResponseException e) {
            log.error("download document error : {} {}", e.getStatusCode().value(), e.getResponseBodyAsString());
            throw new RuntimeException(e);
        }
    }
}
