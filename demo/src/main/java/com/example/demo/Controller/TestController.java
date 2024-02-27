package com.example.demo.Controller;

import com.example.demo.Export.ExportExcelExample;
import com.example.demo.Export.ExportPdfExample;
import com.example.demo.Model.UserDTO;
import com.example.demo.Service.TestService;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class TestController {
    @Autowired
    private ExportPdfExample exportPdfExample;

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok().body("Success");
    }

    @GetMapping("/stream")
    public String stream () {
        return "stream";
    }

    @GetMapping("export-excel")
    public void exportExcel(HttpServletResponse response) {
        try {
            response.setContentType("application/octet-stream");
            DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
            String currentDateTime = dateFormatter.format(new Date());
            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
            response.setHeader(headerKey, headerValue);

            List<UserDTO> userDTOS = new ArrayList<>();
            userDTOS.add(new UserDTO(1, "Quang", 23));
            userDTOS.add(new UserDTO(2, "Quang 2", 24));
            userDTOS.add(new UserDTO(3, "Quang 3", 25));

            ExportExcelExample exportExcelExample = new ExportExcelExample(userDTOS);
            exportExcelExample.exportExcel(response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("export-pdf")
    public ResponseEntity<?> exportPdf(HttpServletResponse response) {
        try {
            ByteArrayInputStream byteArrayInputStream = exportPdfExample.exportPdf();
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=cardIdImages.pdf");
            IOUtils.copy(byteArrayInputStream, response.getOutputStream());
            return ResponseEntity.ok().body("success");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok().body("error");
        }
    }
}
