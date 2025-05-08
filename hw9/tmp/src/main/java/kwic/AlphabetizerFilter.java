package hw9.tmp.src.main.java.kwic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlphabetizerFilter extends Filter {
    public AlphabetizerFilter(PipedReader input, PipedWriter output) {
        super(input, output);
    }

    public void run() {
        try (BufferedReader reader = new BufferedReader(input)) {
            List<String> lines = new ArrayList<>();
            String line;

            while((line = reader.readLine()) != null) {
                lines.add(line);
            }

            Collections.sort(lines, String.CASE_INSENSITIVE_ORDER);

            for (String sortedLine : lines) {
                output.write(sortedLine + "\n");
            }
            output.flush();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
