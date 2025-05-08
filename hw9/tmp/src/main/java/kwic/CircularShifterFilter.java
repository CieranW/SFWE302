package hw9.tmp.src.main.java.kwic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

public class CircularShifterFilter extends Filter{
    public CircularShifterFilter(PipedReader input, PipedWriter output) {
        super(input, output);
    }

    public void run() {
        try (BufferedReader reader = new BufferedReader(input)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");

                for (int i = 0; i < words.length; i++) {
                    StringBuilder shiftedLine = new StringBuilder();
                    for (int j = 0; j < words.length; j++) {
                        shiftedLine.append(words[(i + j) % words.length]).append(" ");
                    }
                    output.write(shiftedLine.toString().trim() + "\n");
                }
                output.flush();
            }
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
