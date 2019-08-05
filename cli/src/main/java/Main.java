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
