package com.assignment4;

import java.io.BufferedReader;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        MyListDemo3 myListDemo = new MyListDemo3();
        myListDemo.loadData();

        System.out.println(myListDemo.list.size());
        myListDemo.createXLS();
    }

    public final void loadData() {
        URL url = null;
        BufferedReader in = null;

        try {
            url = new URL("https://sample-videos.com/csv/Sample-Spreadsheet-1000-rows.csv");
            in = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.ISO_8859_1));
            String inputLine;
            
            while ((inputLine = in.readLine()) != null) {
                processLine(inputLine);
            }
        } catch (MalformedURLException e2) {
            e2.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (in != null ) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    protected void createXLS() {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("sheet1");
            int rownum = 0;

            for (List<String> line : list) {
                Row row = sheet.createRow(rownum++);
                createList(line, row);
            }

            FileOutputStream out = new FileOutputStream(new File("NewFile3.xlsx"));
            workbook.write(out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }   
    }

    private void createList(List<String> line, Row row) {
        Cell cell = row.createCell(0);
        cell.setCellValue(line.get(0));

        cell = row.createCell(1);
        cell.setCellValue(line.get(1));

        cell = row.createCell(2);
        cell.setCellValue(line.get(2));

        cell = row.createCell(3);
        cell.setCellValue(line.get(3));

        cell = row.createCell(4);
        cell.setCellValue(line.get(4));

        cell = row.createCell(5);
        cell.setCellValue(line.get(5));

        cell = row.createCell(6);
        cell.setCellValue(line.get(6));
    }
}
