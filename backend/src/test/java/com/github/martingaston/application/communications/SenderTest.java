package com.github.martingaston.application.communications;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.assertj.core.api.Assertions.*;

@DisplayName("A Sender class")
class SenderTest {
    @DisplayName("Can send a message")
    @Test
    void canSendMessages() throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Sender sender = new Sender(output);
        sender.send("Down\r\nthe rabbit hole");

        assertThat(output.toByteArray()).isEqualTo("Down\r\nthe rabbit hole".getBytes());
    }
}