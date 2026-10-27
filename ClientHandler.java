import java.io.*;
import java.net.*;

public class ClientHandler implements Runnable {
    private Socket socket;
    private GameServer gameServer;
    private Player player;
    private PrintWriter out;

    public ClientHandler(Socket socket, GameServer gameServer) {
        this.socket = socket;
        this.gameServer = gameServer;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            String message;
            while ((message = in.readLine()) != null) {

                String[] parts = message.split("\\|");
                String command = parts[0];


                if (command.equals("JOIN")) {
                    String name = parts[1];
                    this.player = new Player(name);
                    gameServer.broadcastLobby();
                } else if (command.equals("START_GAME")) {
                    gameServer.startGame();
                } else if (command.equals("ANSWER")) {
                    if (player != null) {
                        int timeLeft = Integer.parseInt(parts[1]);
                        player.addScore(timeLeft);
                        player.nextWord();
                        gameServer.updateAndBroadcastScores();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Player disconnected: " + player.getName());
        }
    }

    public void closeConnection() {
        try {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        } catch (IOException e) {
        }
    }

    public void sendMessage(String msg) {
        if (out != null) {
            out.println(msg);
        }
    }

    public Player getPlayer() {
        return player;
    }
}