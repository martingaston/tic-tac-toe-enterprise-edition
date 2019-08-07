package com.github.martingaston.application;

import com.github.martingaston.application.http.*;
import com.github.martingaston.application.routes.Routes;
import com.github.martingaston.application.transport.Connection;
import com.github.martingaston.application.transport.Port;
import com.github.martingaston.application.transport.Server;
import com.github.martingaston.tictactoe.api.API;

import java.io.IOException;

class App {
    private static final String PORT_PROPERTY = "app.port";
    private static final int DEFAULT_PORT = 5000;
    private Server connection;
    private Runner running;

    public App(Server connection) {
        this(connection, new LiveRunning());
    }

    public App(Server connection, Runner running) {
        this.connection = connection;
        this.running = running;
    }

    public static void main(String[] args) throws IOException {
        var port = new Port(getPortNumber());
        var connection = new Connection(port);
        var app = new App(connection);

        app.listen();
    }

    private static int getPortNumber() {
        String portProperty = System.getProperty(PORT_PROPERTY);

        if (portProperty == null) {
            return DEFAULT_PORT;
        }

        return Integer.parseInt(portProperty);
    }

    public void listen() throws IOException {
        while (running.isRunning()) {
            Client client = connection.awaitClient();
            Request request = RequestParser.from(client);
            Routes routes = new Routes();

            routes.get("/api/startGame", (req, res) -> res.send(API.startGame()));

            routes.post("/api/getNextTurn", (req, res) -> {
                String gameRequestAsJson = req.body().toString();
                String ticTacToeResult = API.getNextTurn(gameRequestAsJson);
                return res.send(ticTacToeResult);
            });

            routes.post("/echo_body", (req, res) -> res.send(req.body()));

            Router router = new Router(routes);
            Response response = router.respond(request);

            client.send(ResponseSender.from(response));
        }
    }
}

