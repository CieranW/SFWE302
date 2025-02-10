package com.example;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class Assignment2Test {

    private static final String TEST_CSV = "sample.csv";  // Use actual CSV file
    private static final String TEST_PDF = "output.pdf";
    private static final String TEST_XLSX = "output.xlsx";

    @Test
    void testReadCsv() {
        File file = new File(TEST_CSV);
        assertTrue(file.exists(), "CSV file should exist for testing");

        List<List<String>> data = Assignment2.readCsv(file);

        assertNotNull(data, "CSV data should not be null");
        assertFalse(data.isEmpty(), "CSV should not be empty");
    }

    @Test
    void testGeneratePDF() {
        File file = new File(TEST_CSV);
        List<List<String>> data = Assignment2.readCsv(file);

        Assignment2.generatePDF(TEST_PDF, data);

        assertTrue(Files.exists(Paths.get(TEST_PDF)), "PDF file should be created");
    }

    @Test
    void testGenerateXLSX() {
        File file = new File(TEST_CSV);
        List<List<String>> data = Assignment2.readCsv(file);

        Assignment2.generateXLSX(TEST_XLSX, data);

        assertTrue(Files.exists(Paths.get(TEST_XLSX)), "XLSX file should be created");
    }

    @Test
    void testMain_FileNotFound() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Assignment2.main(new String[]{"PDF", "nonexistent.csv"});

        String output = outContent.toString().trim();
        assertTrue(output.contains("Error: File nonexistent.csv not found"), "Should show file not found error");
    }

    @Test
    void testMain_ValidPDF() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Assignment2.main(new String[]{"PDF", TEST_CSV});

        assertTrue(Files.exists(Paths.get(TEST_PDF)), "PDF file should be generated");
    }

    @Test
    void testMain_ValidXLSX() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Assignment2.main(new String[]{"XLSX", TEST_CSV});

        assertTrue(Files.exists(Paths.get(TEST_XLSX)), "XLSX file should be generated");
    }
}