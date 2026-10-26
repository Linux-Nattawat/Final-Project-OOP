import java.io.*;
import java.net.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.ArrayList;

public class GameServer {
    private final int PORT = 12345;
    private CopyOnWriteArrayList<ClientHandler> clients = new CopyOnWriteArrayList<>();
    private String gameState = "LOBBY"; // LOBBY, PLAYING, ENDED

    public GameServer() {
        startServerThread();
    }

    private void startServerThread() {
        new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(PORT);
                System.out.println("Server started on port :" + PORT);

                while (true) {
                    Socket socket = serverSocket.accept();

                    if (gameState.equals("PLAYING")) {
                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                        out.println("ERROR|Game already started");
                        socket.close();
                        continue;
                    }

                    ClientHandler handler = new ClientHandler(socket, this);
                    clients.add(handler);
                    new Thread(handler).start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void broadcast(String message) {
        for (ClientHandler client : clients) {
            client.sendMessage(message);
        }
    }

    public void startGame() {
        gameState = "PLAYING";
        
        ArrayList<Word> words = WordManager.getRandomGameWords();
        
        StringBuilder sb = new StringBuilder("START|");
        for (int i = 0; i < words.size(); i++) {
            sb.append(words.get(i).getText());
            if (i < words.size() - 1) {
                sb.append(",");
            }
        }

        broadcast(sb.toString());
    }

    public void updateAndBroadcastScores() {
        StringBuilder sb = new StringBuilder("SCORE_UPDATE|");
        
        for (int i = 0; i < clients.size(); i++) {
            Player p = clients.get(i).getPlayer();
            if (p != null) {
                sb.append(p.getName()).append(":").append(p.getScore());
                if (i < clients.size() - 1) {
                    sb.append(",");
                }
            }
        }

        broadcast(sb.toString());
    }

    public void broadcastLobby() {
        StringBuilder sb = new StringBuilder("LOBBY|");
        boolean first = true;
        
        for (ClientHandler client : clients) {
            Player p = client.getPlayer();
            if (p != null) {
                if (!first) {
                    sb.append(",");
                }
                sb.append(p.getName());
                first = false;
            }
        }
        broadcast(sb.toString());
    }
}