package com.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.properties.UnitValue;

public class Assignment2 
{
    public static void main( String[] args )
    {
        if (args.length < 2) {
            System.out.println("Usage: java -jar assignment2.jar <PDF|XLSX> <file name>");
            return;
        }

        String fileType = args[0].toUpperCase();
        String fileName = args[1];

        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("Error: File " + fileName + " not found");
            return;
        }

        List<List<String>> data = readCsv(file);

        if ("PDF".equals(fileType)) {
            generatePDF(fileName.replace(".csv", ".pdf"), data);
        }
        else if ("XLSX".equals(fileType)) {
            generateXLSX(fileName.replace(".csv", ".xlsx"), data);
        }
        else {
            System.out.println("Error: Invalid file type");
        }
    }

    public static List<List<String>> readCsv(File file) {
        List<List<String>> data = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                data.add(Arrays.asList(values));
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return data;
    }

    public static void generatePDF(String fileName, List<List<String>> data) {
        try (PdfWriter writer = new PdfWriter(fileName);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf)) {

            com.itextpdf.layout.element.Table table = 
                new com.itextpdf.layout.element.Table(UnitValue.createPercentArray(data.get(0).size()))
                .useAllAvailableWidth();

            // Add headers
            for (String header : data.get(0)) {
                table.addHeaderCell(new com.itextpdf.layout.element.Cell().add(new com.itextpdf.layout.element.Paragraph(header)));
            }

            // Add table data
            for (int i = 1; i < data.size(); i++) {
                for (String cell : data.get(i)) {
                    table.addCell(new com.itextpdf.layout.element.Cell().add(new com.itextpdf.layout.element.Paragraph(cell)));
                }
            }

            document.add(table);
            System.out.println("PDF file generated: " + fileName);
        } catch (Exception e) {
            System.out.println("Error generating PDF: " + e.getMessage());
        }
    }

    public static void generateXLSX(String fileName, List<List<String>> data) {
        try(Workbook workbook = new XSSFWorkbook();
            FileOutputStream fileOut = new FileOutputStream(fileName)) {
            
            Sheet sheet = workbook.createSheet("Sheet1");

            int rowNum = 0;

            for (List<String> rowData : data) {
                Row row = sheet.createRow(rowNum++);
                int colNum = 0;
                for (String cellData : rowData) {
                    org.apache.poi.ss.usermodel.Cell cell = row.createCell(colNum++);
                    cell.setCellValue(cellData);
                }
            }

            workbook.write(fileOut);
            workbook.close();
            System.out.println("XLSX file generated: " + fileName);
        } catch (IOException e) {
            System.out.println("Error generating XLSX: " + e.getMessage());
        }
    }
}
