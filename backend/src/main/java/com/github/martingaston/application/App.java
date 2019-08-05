package com.github.martingaston.application;

import com.github.martingaston.application.http.*;
import com.github.martingaston.application.routes.Routes;
import com.github.martingaston.application.transport.Connection;
import com.github.martingaston.application.transport.Port;
import com.github.martingaston.application.transport.Server;

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

            routes.post(URI.from("/echo_body"), (req, res) -> res.send(req.body()));

            routes.head(URI.from("/get_with_body"));

            routes.get(URI.from("/simple_get"));

            routes.get(URI.from("/method_options"));

            routes.get(URI.from("/method_options2"));

            routes.post(URI.from("/method_options2"));

            routes.put(URI.from("/method_options2"));

            routes.get(URI.from("/redirect"), (req, res) -> {
                URI location = URI.from("http://" + req.getHeader("Host") + "/simple_get");
                return res.redirect(Status.MOVED_PERMANENTLY, location);
            });

            Router router = new Router(routes);
            Response response = router.respond(request);

            client.send(ResponseSender.from(response));
        }
    }
}

