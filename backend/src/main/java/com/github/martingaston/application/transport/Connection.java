package com.github.martingaston.application.transport;

import com.github.martingaston.application.Client;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Connection implements Server {
    private ServerSocket server;

    public Connection(Port port) throws IOException {
        this.server = new ServerSocket(port.get());
    }

    @Override
    public Client awaitClient() throws IOException {
        Socket socket = server.accept();
        return new Client(socket.getInputStream(), socket.getOutputStream());
    }
}
