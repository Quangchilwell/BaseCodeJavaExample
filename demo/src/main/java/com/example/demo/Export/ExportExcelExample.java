package com.example.demo.Export;

import com.example.demo.Model.UserDTO;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static org.apache.poi.ss.util.CellUtil.createCell;

@Component
public class ExportExcelExample {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    private final List<UserDTO> userDTOS;

    public ExportExcelExample(List<UserDTO> userDTOS) {
        this.userDTOS = userDTOS;
        workbook = new XSSFWorkbook();
    }

    public void exportExcel(HttpServletResponse response) throws Exception {
        writeHeaderLine();
        writeDataLines();
        response.setCharacterEncoding("UTF-8");
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }

    private void writeHeaderLine() {
        sheet = workbook.createSheet("user");
        Row row0 = sheet.createRow(0);
        Row row1 = sheet.createRow(1);
        Row row2 = sheet.createRow(2);
        CellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        createCell(row0, 0, "UserID", style);
        createCell(row1, 0, "", style);
        createCell(row2, 0, "", style);
        CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 2, 0, 0);
        sheet.addMergedRegion(cellRangeAddress);
        sheet.setColumnWidth(0, 3000);

        createCell(row0, 1, "Full name", style);
        createCell(row1, 1, "", style);
        createCell(row2, 1, "", style);
        CellRangeAddress cellRangeAddress1 = new CellRangeAddress(0, 2, 1, 1);
        sheet.addMergedRegion(cellRangeAddress1);
        sheet.setColumnWidth(1, 3000);

        createCell(row0, 2, "Age", style);
        createCell(row1, 2, "", style);
        createCell(row2, 2, "", style);
        CellRangeAddress cellRangeAddress2 = new CellRangeAddress(0, 2, 2, 2);
        sheet.addMergedRegion(cellRangeAddress2);
        sheet.setColumnWidth(2, 3000);
    }

    private void writeDataLines() {
        int rowCount = 3;
//        int column++ = 0;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        style.setWrapText(true);

        for (UserDTO dto: userDTOS) {
            Row row = sheet.createRow(rowCount++);
            int column = 0;
            createCell(row, column++, String.valueOf(dto.getId()), style);
            createCell(row, column++, dto.getName(), style);
            createCell(row, column++, String.valueOf(dto.getAge()), style);
        }
    }
}
