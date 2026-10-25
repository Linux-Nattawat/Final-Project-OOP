import java.io.*;
import java.net.*;
import java.util.Scanner;

public class NetworkManager {

    final int PORT = 12345;

    NetworkManager() {
         try {
            Scanner scanner = new Scanner(System.in);
            Socket socket = new Socket("localhost", PORT);
            PrintStream out = new PrintStream(socket.getOutputStream());

            InetAddress myAddr = InetAddress.getLocalHost();
            while (true){
                System.out.print("Input Text : ");
                String text = scanner.nextLine();
                
                String msg = myAddr.getHostName() + " # " + text;
                out.println(msg);
                Thread.sleep(500); // Wait for 1 second before sending the next message  
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
