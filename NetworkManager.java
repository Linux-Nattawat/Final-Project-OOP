import java.io.*;
import java.net.*;
import javax.swing.*;

public class NetworkManager implements Runnable {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private MainFrame mainFrame;

    public NetworkManager(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public boolean connect(String ip, int port, String playerName) {
        try {
            socket = new Socket(ip, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            sendMessage("JOIN|" + playerName);

            new Thread(this).start();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void run() {
        try {
            String message;
            while ((message = in.readLine()) != null) {
                String[] parts = message.split("\\|");
                String command = parts[0];

                if (command.equals("START")) {
                    String[] words = parts[1].split(",");
                    mainFrame.startGame(words);

                } else if (command.equals("SCORE_UPDATE")) {
                    String scoreData = parts[1];
                    SwingUtilities.invokeLater(() -> {
                        mainFrame.updateScore(scoreData);
                    });

                } else if (command.equals("LOBBY")) {
                    String[] players = parts[1].split(",");

                    SwingUtilities.invokeLater(() -> {
                        mainFrame.updateLobby(players);
                    });
                }

            }
        } catch (Exception e) {
            System.out.println("Disconnected from server.");
        }
    }

    public void sendMessage(String msg) {
        if (out != null) {
            out.println(msg);
        }
    }

    public void sendAnswer(int timeLeft) {
        sendMessage("ANSWER|" + timeLeft);
    }

    public void disconnect() {
        try {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
            if (in != null)
                in.close();
            if (out != null)
                out.close();
        } catch (Exception e) {
            System.out.println("Error disconnecting: " + e.getMessage());
        }

    }
}