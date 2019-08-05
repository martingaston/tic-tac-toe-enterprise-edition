package com.github.martingaston.application.transport;

import com.github.martingaston.application.Client;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class MockConnection implements Server {
    private ByteArrayInputStream in;
    private ByteArrayOutputStream out;
    private int timesAwaitClientCalled = 0;

    public MockConnection(byte[] request) {
        in = new ByteArrayInputStream(request);
        out = new ByteArrayOutputStream();
    }

    public byte[] received() {
        return out.toByteArray();
    }

    public int awaitClientCalledXTimes() {
        return timesAwaitClientCalled;
    }

    @Override
    public Client awaitClient() throws IOException {
        timesAwaitClientCalled += 1;
        return new Client(in, out);
    }
}
