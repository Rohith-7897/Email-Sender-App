package com.example.bulkemailsender.util;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class BulkExcelReader {

    public List<String> readEmailAddresses(String filePath) throws IOException {
        List<String> emailAddresses = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(filePath);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
            var sheet = workbook.getSheetAt(0); // Read the first sheet
            for (Row row : sheet) {
                String email = row.getCell(0).getStringCellValue(); // Assumes email addresses are in the first column
                emailAddresses.add(email);
            }
        }
        return emailAddresses;
    }
}