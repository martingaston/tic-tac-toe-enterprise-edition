package com.github.martingaston.application.transport;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;

class PortTest {
    @Test
    void createsOnRegisteredPort() {
        var port = new Port(5000);
        assertThat(port.get()).isEqualTo(5000);
    }

    @Test
    void createsOnEphemeralPort() {
        var port = new Port(65535);
        assertThat(port.get()).isEqualTo(65535);
    }

    @Test
    void willNotCreateOnSystemPort() {
        var thrown = catchThrowable(() -> new Port(80));
        assertThat(thrown).hasMessageContaining("Invalid port");
    }
}
