package com.github.martingaston;

import com.github.martingaston.state.State;
import com.github.martingaston.state.StateCLI;
import com.github.martingaston.state.StateFile;
import com.github.martingaston.state.StateUserSelect;
import com.github.martingaston.storage.FileStorage;
import com.github.martingaston.storage.Storage;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        IO io = new IO(new Scanner(System.in));

        Storage storage = new FileStorage();
        List<String> prevState = storage.in();

        State state;
        if (!prevState.isEmpty()) {
            state = new StateFile(prevState, io);
        } else if (args.length > 0) {
            state = new StateCLI(args, io);
        } else {
            state = new StateUserSelect(io);
        }

        Game.play(state, storage);
    }
}
