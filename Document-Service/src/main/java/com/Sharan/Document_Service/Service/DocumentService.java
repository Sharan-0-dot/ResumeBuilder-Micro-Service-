package com.Sharan.Document_Service.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public byte[] download(String resume) throws Exception {
        try {
            return createFile(resume);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] createFile(String resumeText) throws Exception {
        XWPFDocument document = new XWPFDocument();

        // Reduce margins (all sides = 0.5 inch ~ 720 twips)
        int margin = 720;
        document.getDocument().getBody().addNewSectPr().addNewPgMar()
                .setLeft(margin);
        document.getDocument().getBody().getSectPr().getPgMar().setRight(margin);
        document.getDocument().getBody().getSectPr().getPgMar().setTop(margin);
        document.getDocument().getBody().getSectPr().getPgMar().setBottom(margin);

        // Split sections based on triple newlines
        String[] sections = resumeText.split("\n\\n\\n");

        boolean isFirstSection = true;
        for (String section : sections) {
            section = section.trim();
            if (section.isEmpty()) continue;

            String[] lines = section.split("\n");

            // First section = personal info
            if (isFirstSection) {
                for (int i = 0; i < lines.length; i++) {
                    String line = lines[i].trim();
                    if (line.isEmpty()) continue;

                    XWPFParagraph p = document.createParagraph();
                    p.setAlignment(ParagraphAlignment.CENTER);
                    p.setSpacingBefore(0);
                    p.setSpacingAfter(0);

                    XWPFRun run = p.createRun();
                    run.setFontSize(i == 0 ? 18 : 12);
                    if (i == 0) {
                        run.setBold(true);
                        run.setColor("000080");
                    }
                    run.setText(line);
                }
                isFirstSection = false;
                continue;
            }

            // Section header = first line
            String header = lines[0].trim();
            addHeading(document, header);

            // Remaining lines = content
            for (int i = 1; i < lines.length; i++) {
                String line = lines[i].trim();
                if (line.isEmpty()) continue;

                XWPFParagraph para = document.createParagraph();
                para.setAlignment(ParagraphAlignment.BOTH);
                para.setSpacingBefore(0);
                para.setSpacingAfter(0);

                XWPFRun run = para.createRun();
                run.setFontSize(12);
                run.setText(line);
            }
        }

        // Write document
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        document.write(out);
        document.close();
        return out.toByteArray();
    }

    private void addHeading(XWPFDocument doc, String text) {
        XWPFParagraph p = doc.createParagraph();
        p.setAlignment(ParagraphAlignment.LEFT);
        p.setSpacingBefore(300); // small spacing before
        p.setSpacingAfter(200);   // small spacing after
        XWPFRun r = p.createRun();
        r.setBold(true);
        r.setUnderline(UnderlinePatterns.SINGLE);
        r.setFontSize(14);
        r.setText(text.toUpperCase());
    }
}
