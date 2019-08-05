package com.github.martingaston.application.communications;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

public class Sender {
    private BufferedWriter writer;

    public Sender(OutputStream stream) {
        this.writer = new BufferedWriter(new PrintWriter(stream));
    }

    public void send(String contents) throws IOException {
        writer.write(contents);
        writer.flush();
    }
}
