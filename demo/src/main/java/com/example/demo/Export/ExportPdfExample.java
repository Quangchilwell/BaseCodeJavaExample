package com.example.demo.Export;

import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.text.pdf.BaseFont;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class ExportPdfExample {

    @Autowired
    private SpringTemplateEngine springTemplateEngine;

    public ExportPdfExample () {

    }

    public ByteArrayInputStream exportPdf() {

        org.thymeleaf.context.Context context = new org.thymeleaf.context.Context();
//        context.setVariable("cardIdDTO", cardIdResponseDTO);
        String template = springTemplateEngine.process("test", context);

        // Ghi nội dung template vào file PDF
        ByteArrayInputStream byteArrayInputStream;
        try (com.itextpdf.io.source.ByteArrayOutputStream byteArrayOutputStream = new com.itextpdf.io.source.ByteArrayOutputStream()) {
            byte[] bytes = template.getBytes(StandardCharsets.UTF_8);
            String utf8EncodedString = new String(bytes, StandardCharsets.UTF_8);
            ITextRenderer renderer = new ITextRenderer();

//          Fonts arial chỉnh sửa chữ có mũ và dấu
            Path path2 = Paths.get(new ClassPathResource("/fonts/arial.ttf").getPath());
            renderer.getFontResolver().addFont(path2.toString(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            renderer.setDocumentFromString(utf8EncodedString);
            renderer.layout();
            renderer.createPDF(byteArrayOutputStream);
            renderer.finishPDF();
            byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return byteArrayInputStream;
    }
}
