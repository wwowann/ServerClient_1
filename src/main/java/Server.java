import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            Socket socket = serverSocket.accept();
            try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(
                         new InputStreamReader(socket.getInputStream()))) {
                String line;
                while ((line = in.readLine()) != null) {
                    if (line.equals("end")) {
                        break;
                    }
                    try {
                        out.println("Echo: Число Фибоначчи равно " + getFibonacciValue(Integer.parseInt(line)));
                    } catch (NumberFormatException e) {
                        out.println("Echo: ведите целое число");
                    }
                }
                socket.close();
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getFibonacciValue(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            return getFibonacciValue(n - 1) + getFibonacciValue(n - 2);
        }
    }
}
