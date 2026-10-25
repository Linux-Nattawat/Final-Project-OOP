import java.net.*;
import java.io.*;

public class GameServer {
    
    final int PORT = 12345;
    
    GameServer() {
        startServerThread();
    }

    void startServerThread(){
        new Thread(new Runnable() {
            @Override 
            public void run() {
                try {
                    ServerSocket serverSocket = new ServerSocket(PORT);
                    System.out.println("Server is running on port 12345...");
                    Socket socket = serverSocket.accept();

                    while (true) {
                        try {
                            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                            String text = in.readLine();
                            System.out.println("GameServer Received: " + text);
                            
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
