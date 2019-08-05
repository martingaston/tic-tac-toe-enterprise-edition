package com.github.martingaston.application.communications;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Receiver {
    private BufferedReader buffered;

    public Receiver(InputStream stream) {
        this.buffered = new BufferedReader(new InputStreamReader(stream));
    }

    public String receiveLine() throws IOException {
        String received = buffered.readLine();

        if (received == null) {
            return "";
        }

        return received;
    }

    public byte[] drainStream() throws IOException {
        ArrayList<Byte> list = readWhileActive();
        return convertToByteArray(list);
    }

    private ArrayList<Byte> readWhileActive() throws IOException {
        final int EOF = -1;
        ArrayList<Byte> list = new ArrayList<>();

        int data;
        while (buffered.ready() && (data = buffered.read()) != EOF) {
            list.add((byte) data);
        }
        
        return list;
    }

    private byte[] convertToByteArray(ArrayList<Byte> list) {
        byte[] result = new byte[list.size()];

        int index = 0;
        for (Byte current : list) {
            result[index++] = current;
        }

        return result;
    }
}
