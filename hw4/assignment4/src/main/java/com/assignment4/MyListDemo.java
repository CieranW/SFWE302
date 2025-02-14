package com.assignment4;

import java.util.List;

public class MyListDemo extends URLLoader{
    
    protected List<String> list = new ArrayList<>();

    @Override 
    protected void processLine(String tokens) {
        list.add(tokens);
    }

    public static void main(String[] args) {
        MyListDemo myListDemo = new MyListDemo();
        myListDemo.loadData();
        System.out.println(myListDemo.list.size());
    }
}
