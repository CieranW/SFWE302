package com.assignment4;

public class App {
    public static void main(String[] args) {
        MyListDemo2 myListDemo = new MyListDemo2();
        
        // Load Data
        System.out.println("Loading Data...");
        myListDemo.loadData();
        System.out.println(myListDemo.list.size());

        // Apply Search
        System.out.println("Applying Search...");
        myListDemo.applySearch();
        
        // Create XLS
        System.out.println("Creating XLS...");
        myListDemo.createXLS();
    }
}