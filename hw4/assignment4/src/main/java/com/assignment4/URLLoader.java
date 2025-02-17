package com.assignment4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public abstract class URLLoader {
    protected abstract void processLine(String[] tokens);

    public final void loadData() {
        URL url = null;
        BufferedReader in = null;

        try {
            url = new URL("https://sample-videos.com/csv/Sample-Spreadsheet-1000-rows.csv");
            in = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.ISO_8859_1));
            String inputLine;
            
            while ((inputLine = in.readLine()) != null) {
                processLine(split(inputLine));
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

    protected String[] split(String inputLine) {
        String[] tokens = inputLine.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        
        for(int i = 0; i < tokens.length; i++) {
            tokens[i] = tokens[i].replaceAll("^\"|\"$", "").replaceAll("\"\"", "\"");
        }
        return tokens;
    }    
}
