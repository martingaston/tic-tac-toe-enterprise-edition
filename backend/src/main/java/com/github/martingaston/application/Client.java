package com.github.martingaston.application;

import com.github.martingaston.application.communications.Receiver;
import com.github.martingaston.application.communications.Sender;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class Client {
    private Sender sender;
    private Receiver receiver;

    public Client(InputStream in, OutputStream out) {
        this.sender = new Sender(out);
        this.receiver = new Receiver(in);
    }

    public String receive() throws IOException {
        return receiver.receiveLine();
    }

    public String receiveBody() throws IOException {
        return new String(receiver.drainStream(), StandardCharsets.UTF_8);
    }

    public void send(String response) throws IOException {
        sender.send(response);
    }
}
