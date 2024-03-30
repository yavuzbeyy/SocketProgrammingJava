package TCP;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.UnknownHostException;
import java.net.Socket;

public class TCPIstemci {

    public static void main(String[] args) throws IOException {

        Socket socket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        final String hOST = ""; // Sunucu IP adresi
        final int pORT = 8088; // Sunucu port numarası

        try {
            socket = new Socket(hOST, pORT);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Sunucu bulunamadı");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("I/O hatası: " + e.getMessage());
            System.exit(1);
        }

        System.out.println("Sunucuya bağlanıldı.");

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Büyük harflere çevrilmesi için girdi bekleniyor (bağlantıyı kesmek için: son) ...");

        String userInput;
        while (!(userInput = stdIn.readLine()).equals("son")) {
            out.println(userInput);
            System.out.println("Sunucudan gelen: " + in.readLine());
        }

        out.close();
        in.close();
        stdIn.close();
        socket.close();
    }
}
