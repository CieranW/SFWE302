package com.assignment4;

public abstract class URLLoader {
    protected abstract void processLine(String tokens){
        System.out.println(tokens);
    }
}
