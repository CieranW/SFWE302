package com.assignment4;

import java.util.ArrayList;
import java.util.List;

public class MyListDemo extends URLLoader{
    
    protected List<Product> list = new ArrayList<>();

    @Override
    protected void processLine(String[] tokens) {
        if (tokens.length < 7) return; // Ensure valid data

        Product product = new Product();
        product.setId(Long.parseLong(tokens[0])); // Convert String to Long
        product.setName(tokens[1]);
        product.setAgentName(tokens[2]);
        product.setAgentId(Long.parseLong(tokens[3]));
        product.setPrice(Double.parseDouble(tokens[4])); // Convert to Double
        product.setTerritory(tokens[5]);
        product.setCategory(tokens[6]);

        list.add(product); // Now correctly adding a Product, not a String[]
    }


}
