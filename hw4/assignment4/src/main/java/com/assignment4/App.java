package com.assignment4;

import java.io.BufferedReader;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }

    public final void loadData() {
        URL url = null;
        BufferedReader in = null;

        try {
            url = new URL("https://sample-videos.com/csv/Sample-Spreadsheet-1000-rows.csv");
            in = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.ISO_8859_1));
            String inputLine;
            
            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
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
}
