package com.github.martingaston.application.communications;

import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.assertj.core.api.Assertions.*;

@DisplayName("A Receiver class")
class ReceiverTest {
    @DisplayName("Can receiveLine messages")
    @Test
    void canReceiveMessage() throws IOException {
        ByteArrayInputStream input = new ByteArrayInputStream("It was the best of times.\r\nIt was the worst of times.".getBytes());
        Receiver receiver = new Receiver(input);

        assertThat(receiver.receiveLine()).isEqualTo("It was the best of times.");
        assertThat(receiver.receiveLine()).isEqualTo("It was the worst of times.");

    }

    @DisplayName("Will return no/null input as an empty string")
    @Test
    void returnsEmptyStringOnNoInput() throws IOException {
        ByteArrayInputStream input = new ByteArrayInputStream("".getBytes());
        Receiver receiver = new Receiver(input);

        assertThat(receiver.receiveLine()).isEqualTo("");
    }
}