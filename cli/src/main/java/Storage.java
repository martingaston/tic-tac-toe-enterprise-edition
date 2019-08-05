import java.io.IOException;
import java.util.List;

public interface Storage {
    List<String> in();

    void out(State state) throws IOException;

    void close();
}
