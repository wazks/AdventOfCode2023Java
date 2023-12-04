import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class InputReader {
    private BufferedReader reader;

    public InputReader(String path) {
        try {
            reader = new BufferedReader(new FileReader(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
