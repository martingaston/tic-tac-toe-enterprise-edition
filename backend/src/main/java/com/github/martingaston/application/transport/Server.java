package com.github.martingaston.application.transport;

import com.github.martingaston.application.Client;

import java.io.IOException;

public interface Server {
    Client awaitClient() throws IOException;
}
