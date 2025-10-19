package com.Sharan.AI_Service.Service;

import com.Sharan.AI_Service.Model.AiResponse;
import com.Sharan.AI_Service.Model.Resume;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@RequiredArgsConstructor
@Slf4j
public class AIResumeService {

    private final ResponseService service;
    private final GeminiService geminiService;
    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule()).disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    public String getResponse(Resume resume) {
        log.info("Received request");
        String prompt = getPrompt(resume);
        String response = geminiService.getAIResume(prompt);
        log.info("RESPONSE FROM AI {}", response);
        String savedResponse = processResponse(response);
        service.saveResponse(savedResponse);
        return savedResponse;
    }

    private String processResponse(String response) {
        try {
            JsonNode rootNode = objectMapper.readTree(response);
            JsonNode textNode = rootNode.path("candidates")
                    .get(0)
                    .path("content")
                    .get("parts")
                    .get(0)
                    .get("text");
            return textNode.asText();
        } catch (Exception e) {
            e.printStackTrace();
            return "The resume Could not be generated please try again after sometimes";
        }
    }

    private String getPrompt(Resume resume) {
        String jsonResume;
        try {
            jsonResume = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(resume);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting resume to JSON", e);
        }
        return "You are an expert resume writer who creates clean, ATS-friendly, and recruiter-appealing resumes.\n" +
                "Generate the resume in **plain text only** — do NOT use Markdown, HTML, or any special characters like '*', '**', '_', etc.\n" +
                "Using the JSON object provided below (which represents a candidate’s full resume information), " +
                "generate a professional resume in text format.\n\n" +

                "Formatting rules:\n" +
                "- Separate each major section with EXACTLY THREE blank lines.\n" +
                "- Within a section, separate items (like experiences or projects) with ONE blank line.\n" +
                "- Keep section titles in UPPERCASE (e.g., EXPERIENCE, EDUCATION, SKILLS).\n" +
                "- Keep the design minimal and modern (no tables, just well-spaced text).\n" +
                "- Quantify achievements where possible (e.g., “Improved performance by 20%”).\n" +
                "- Keep the language impactful and concise — no generic filler words.\n" +
                "- Highlight technical skills, certifications, and projects clearly.\n\n" +

                "Required sections:\n" +
                "1. Header (Name, Role, Contact Info)\n" +
                "2. Professional Summary (2–3 impactful lines)\n" +
                "3. Skills (grouped by categories if available)\n" +
                "4. Experience (each with role, company, duration, and bullet points)\n" +
                "5. Projects (each with name, description, tech stack, and impact)\n" +
                "6. Education (degree, college, duration, and achievements)\n" +
                "7. Certifications (if any)\n" +
                "8. Achievements / Awards (optional)\n\n" +

                "JSON Input (Resume Object):\n" +
                jsonResume + "\n\n" +
                "End the resume cleanly without any unnecessary closing text.";
    }
}
