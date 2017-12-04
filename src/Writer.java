import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class Writer extends Thread {
    private BufferedWriter out;

    Writer(OutputStream os) {
        this.out = new BufferedWriter(new OutputStreamWriter(os));
    }

    public void writeLine(String line) {
        write(line + "\n");
    }

    public void write(String line) {
        try {
            out.write(line);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
