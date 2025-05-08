package hw9.tmp.src.main.java.kwic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PipedWriter;

public class InputFilter extends Filter{
    private BufferedReader fileReader;

    public InputFilter(FileReader fileReader, PipedWriter output) {
        super(null, output);
        this.fileReader = new BufferedReader(fileReader);
    }

    public void run() {
        try {
            String line;
            while((line = fileReader.readLine()) != null) {
                output.write(line + "\n");
                output.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
