import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame {

    private NetworkManager networkManager;
    private GamePanel currentPanel;
    private JPanel currentScreen;

    public MainFrame() {
        setTitle("Word Running");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.networkManager = new NetworkManager(this);
        switchScreen(new MainMenu(this));
    }

    public void switchScreen(JPanel newScreen) {
        if (currentScreen != null) {
            remove(currentScreen);
        }
        currentScreen = newScreen;
        add(currentScreen);
        revalidate();
        repaint();
    }

    public void switchToLobby() {
        switchScreen(new Lobby(this));
    }

    public void startGame(String[] words) {
        SwingUtilities.invokeLater(() -> {
            this.currentPanel = new GamePanel(this, words);
            switchScreen(currentPanel);
        });
    }

    public void updateScore(String scoreData) {
        SwingUtilities.invokeLater(() -> {
            if (currentPanel != null) {
                currentPanel.updateLeaderboard(scoreData);
            }
        });
    }

    public void updateLobby(String[] players) {
        System.out.println("กำลังอัปเดต Lobby ตอนนี้มีคน: " + players.length);

        if (currentScreen instanceof Lobby) {
            Lobby lobby = (Lobby) currentScreen;
            lobby.updateLobby(players);
            lobby.revalidate();
            lobby.repaint();
            System.out.println("อัปเดตแล้ว");
        }

    }

    public NetworkManager getNetworkManager() {
        return networkManager;
    }

    public JPanel getCurrentScreen() {
        return currentScreen;
    }
}