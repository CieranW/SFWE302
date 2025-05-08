package hw9.tmp.src.main.java.kwic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PipedReader;

public class OutputFilter extends Filter {
    public OutputFilter(PipedReader input) {
        super(input, null);
    }

    public void run() {
        try (BufferedReader reader = new BufferedReader(input)) {
            String line;

            while((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
