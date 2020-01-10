import java.io.FileNotFoundException;
import java.io.IOException;

public interface IHandler {

    void read() throws FileNotFoundException, IOException;

    void write() throws FileNotFoundException, IOException;
}
