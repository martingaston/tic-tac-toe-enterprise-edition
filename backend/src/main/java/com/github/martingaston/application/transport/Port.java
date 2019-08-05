package com.github.martingaston.application.transport;

public class Port {
    private final int port;
    private final int MIN_PORT = 1024;
    private final int MAX_PORT = 65535;

    public Port(int port) {
        validatePort(port);

        this.port = port;
    }

    private void validatePort(int port) {
        if (port < MIN_PORT || port > MAX_PORT) {
            throw new IllegalArgumentException("Invalid port specified");
        }
    }

    public int get() {
        return this.port;
    }
}
