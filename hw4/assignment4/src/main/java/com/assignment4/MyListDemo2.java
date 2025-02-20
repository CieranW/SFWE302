package com.assignment4;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.Map.Entry;
import java.util.*;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class MyListDemo2 extends URLLoader{
    
    protected List<Product> list = new ArrayList<>();
    protected Set<String> resultSet = new HashSet<>();
    protected Set<String> all = new HashSet<>();
    protected Set<String> duplicates = new HashSet<>();
    protected Set<String> oneOccurrence = new HashSet<>();
    protected Map<String, Integer> productCount = new HashMap<>();
    protected Map<String, List<Product>> groupedByTerritory;
    protected Map<String, Double> territoryTotalPrices;

    @Override
    protected void processLine(String[] tokens) {
        if (tokens.length < 9) return; // Ensure valid data
        
        if(tokens[7].equalsIgnoreCase("British Columbia")) {
            Product product = new Product();
            product.setId(Long.parseLong(tokens[0]));
            product.setName(tokens[1]);
            product.setAgentName(tokens[2]);
            product.setAgentId(Long.parseLong(tokens[3]));
            product.setPrice(Double.parseDouble(tokens[5]));
            product.setTerritory(tokens[7]);
            product.setCategory(tokens[8]);

            list.add(product);
        }
    }

    public void createXLS() {
        try {
            System.out.println("📝 Creating Excel file...");
            XSSFWorkbook workbook = new XSSFWorkbook();
            
            // 🟢 Sheet 1: Filtered Product Data
            XSSFSheet sheet1 = workbook.createSheet("FilteredData");
            int rownum = 0;

            // Add header row
            Row headerRow = sheet1.createRow(rownum++);
            String[] headers = {"ID", "Name", "Agent Name", "Agent ID", "Price", "Territory", "Category"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }

            // Add product data
            for (Product product : list) {
                Row row = sheet1.createRow(rownum++);
                createExcelRow(product, row);
            }

            // 🟢 Sheet 2: Summed Prices by Territory
            XSSFSheet sheet2 = workbook.createSheet("TotalPriceByTerritory");
            rownum = 0;

            // Add header
            Row totalHeader = sheet2.createRow(rownum++);
            totalHeader.createCell(0).setCellValue("Territory");
            totalHeader.createCell(1).setCellValue("Total Price");

            // ✅ Sort and group products by territory, summing their prices
            Map<String, Double> territoryPriceMap = list.stream()
                .sorted((o1, o2) -> o1.getTerritory().compareTo(o2.getTerritory()))
                .collect(Collectors.groupingBy(Product::getTerritory, Collectors.summingDouble(Product::getPrice)));

            // 🟢 Write summed price data
            for (Entry<String, Double> result : territoryPriceMap.entrySet()) {
                Row row = sheet2.createRow(rownum++);
                row.createCell(0).setCellValue(result.getKey());  // Territory name
                row.createCell(1).setCellValue(result.getValue()); // Total price
            }

            // 🟢 Sheet 3: Grouped Products by Territory
            XSSFSheet sheet3 = workbook.createSheet("GroupedByTerritory");
            rownum = 0;

            // Add header
            Row groupHeader = sheet3.createRow(rownum++);
            groupHeader.createCell(0).setCellValue("Territory");
            groupHeader.createCell(1).setCellValue("Product Name");

            // ✅ Sort and group products by territory (keeping product lists)
            Map<String, List<Product>> groupedProducts = list.stream()
                .sorted((o1, o2) -> o1.getTerritory().compareTo(o2.getTerritory()))
                .collect(Collectors.groupingBy(Product::getTerritory));

            // 🟢 Write grouped product data
            for (Entry<String, List<Product>> result : groupedProducts.entrySet()) {
                Row row = sheet3.createRow(rownum++);
                Cell cell = row.createCell(0);
                cell.setCellValue(result.getKey()); // Territory Name

                boolean skipLine = true;
                for (Product product : result.getValue()) {
                    if (skipLine) {
                        skipLine = false;
                    } else {
                        row = sheet3.createRow(rownum++);
                    }
                    cell = row.createCell(1);
                    cell.setCellValue(product.getName());
                    cell = row.createCell(2);
                    cell.setCellValue(product.getTerritory());
                }
            }

            // ✅ Write workbook to file
            FileOutputStream out = new FileOutputStream(new File("FilteredData.xlsx"));
            workbook.write(out);
            out.flush();  // ✅ Ensures all data is written
            out.close();
            // workbook.close();

            System.out.println("✅ Excel file created successfully: FilteredData.xlsx");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // Helper method to fill a row with product details
    private void createExcelRow(Product product, Row row) {
        Cell cell = row.createCell(0);
        cell.setCellValue(product.getId());

        cell = row.createCell(1);
        cell.setCellValue(product.getName());

        cell = row.createCell(2);
        cell.setCellValue(product.getAgentName());

        cell = row.createCell(3);
        cell.setCellValue(product.getAgentId());

        cell = row.createCell(4);
        cell.setCellValue(product.getPrice());

        cell = row.createCell(5);
        cell.setCellValue(product.getTerritory());

        cell = row.createCell(6);
        cell.setCellValue(product.getCategory());
    }

    public void countUniqueProductNames() {
        for (Product product : list ) {
            resultSet.add(product.getName());
        }
        System.out.println("Unique Product Names: " + resultSet.size());
    }

    public void findSingleOccurrenceProducts() {
        for (Product product : list) {
            String name = product.getName();
            if (!all.add(name)) {
                duplicates.add(name);
            }
        }
        oneOccurrence = new HashSet<>(all);
        oneOccurrence.removeAll(duplicates);

        System.out.println("Products that appear exactly once: " + oneOccurrence.size());

    }

    public void sortUniqueProductNames() {
        oneOccurrence = new TreeSet<>(oneOccurrence);
        System.out.println("Sorted Unique Product Names: " + oneOccurrence);
    }

    public void countProductOccurrences() {
        for (Product product : list) {
            productCount.put(product.getName(), productCount.getOrDefault(product.getName(), 0) + 1);
        }
        System.out.println("Product Occurrences: " + productCount);
    }

    public Map<String, Integer> sortProductsByCount() {
        return productCount.entrySet().stream().sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    public void groupByTerritory() {
        groupedByTerritory = list.stream().collect(Collectors.groupingBy(Product::getTerritory));

        groupedByTerritory.forEach((territory, products) -> {
            System.out.println("Territory: " + territory);
            products.forEach(product -> {
                System.out.println(product.getName());
            });
        });
    }

    public void groupAndSortByProductName() {
        groupedByTerritory = list.stream().sorted((o1, o2) -> o1.getName().compareTo(o2.getName())).collect(Collectors.groupingBy(Product::getTerritory));

        groupedByTerritory.forEach((territory, products) -> {
            System.out.println("Territory: " + territory);
            products.forEach(product -> System.out.println(" - " + product.getName()));
        });
    }

    public void sumPricesByTerritory() {
        territoryTotalPrices = list.stream().collect(Collectors.groupingBy(Product::getTerritory, Collectors.summingDouble(Product::getPrice)));

        territoryTotalPrices.forEach((territory, total) -> {
            System.out.println("Territory: " + territory + " Total Price: " + total);
        });
    }

    public static <K extends Comparable<? super K>, V extends Comparable<? super V>> LinkedHashMap<K, V>
    sortByValue(Map<K, V> map) {
        List<Entry<K, V>> list = new ArrayList<>(map.entrySet());

        // Sorting by value (descending order)
        list.sort(new Comparator<Map.Entry<K, V>>() {
            @Override
            public int compare(Entry<K, V> o1, Entry<K, V> o2) {
                if (o2.getValue().equals(o1.getValue())) {
                    return o1.getKey().compareTo(o2.getKey()); // Alphabetical order if values are equal
                } else {
                    return o2.getValue().compareTo(o1.getValue()); // Descending order
                }
            }
        });

        // Storing sorted entries in a LinkedHashMap to maintain order
        LinkedHashMap<K, V> result = new LinkedHashMap<>();
        for (Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    public void applySearch() {
        System.out.println("🔎 Applying search and analysis...");

        // ✅ Run all filtering and grouping tasks
        countUniqueProductNames();
        findSingleOccurrenceProducts();
        sortUniqueProductNames();
        countProductOccurrences();
        sortProductsByCount();
        groupByTerritory();
        groupAndSortByProductName();
        
        // ✅ Summing and sorting operations before writing to Excel
        sumPricesByTerritory();
        // groupProductsByTerritory(); 

        System.out.println("✅ Search and analysis complete!");
    }
}
