package com.Sharan.AI_Service.Service;

import com.Sharan.AI_Service.Model.AiResponse;
import com.Sharan.AI_Service.Repository.AIResumeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResponseService {

    private final AIResumeRepo repo;

    public AiResponse saveResponse(String resume) {
        AiResponse response = new AiResponse();
        response.setResponse(resume);
        return repo.save(response);
    }
}
