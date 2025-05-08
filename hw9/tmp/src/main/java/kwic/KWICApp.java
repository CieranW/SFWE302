package hw9.tmp.src.main.java.kwic;

import java.io.*;
import java.util.Scanner;

public class KWICApp {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        while (true) { 
            String line = scanner.nextLine();
            if (line.equalsIgnoreCase("END")) {
                break;
            }
            sb.append(line).append("\n");
        }
        scanner.close();

        FileReader fileReader = new FileReader("input.txt");

        PipedWriter pw1 = new PipedWriter();
        PipedWriter pw2 = new PipedWriter();
        PipedWriter pw3 = new PipedWriter();

        PipedReader pr1 = new PipedReader(pw1);
        PipedReader pr2 = new PipedReader(pw2);
        PipedReader pr3 = new PipedReader(pw3);

        Thread inputFilter = new Thread(new InputFilter(fileReader, pw1));
        Thread circularShifterFilter = new Thread(new CircularShifterFilter(pr1, pw2));
        Thread alphabetizerFilter = new Thread(new AlphabetizerFilter(pr2, pw3));
        Thread outputFilter = new Thread(new OutputFilter(pr3));

        inputFilter.start();
        circularShifterFilter.start();
        alphabetizerFilter.start();
        outputFilter.start();

    }
}
