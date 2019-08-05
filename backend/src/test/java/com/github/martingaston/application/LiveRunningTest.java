package com.github.martingaston.application;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;

@DisplayName("A LiveRunning class")
class LiveRunningTest {
   @DisplayName("Is always true")
   @Test
   void isAlwaysTrue() {
       LiveRunning running = new LiveRunning();
       assertThat(running.isRunning()).isTrue();
   }
}