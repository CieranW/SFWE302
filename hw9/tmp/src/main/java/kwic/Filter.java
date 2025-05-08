package hw9.tmp.src.main.java.kwic;

import java.io.PipedReader;
import java.io.PipedWriter;

public abstract class Filter implements Runnable {
    protected PipedReader input;
    protected PipedWriter output;

    public Filter(PipedReader input, PipedWriter output) {
        this.input = input;
        this.output = output;
    }

    public abstract void run();
}
