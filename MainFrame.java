import javax.swing.*;

public class MainFrame extends JFrame {
    private String serverIp = "127.0.0.1";
    private NetworkManager networkManager;
    private GamePanel currentPanel;
    private JPanel currentScreen;
    private GameServer gameServer;

    public MainFrame() {
        setTitle("Word Running");
        setSize(1000, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
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

    public void startGame(String[] words) {
        SwingUtilities.invokeLater(() -> {
            this.currentPanel = new GamePanel(this, words);
            switchScreen(currentPanel);
        });
    }

    public void updateScore(String scoreData) {
        if (currentScreen instanceof GamePanel) {
            GamePanel game = (GamePanel) currentScreen;
            game.updateLeaderboard(scoreData);
        }
        if (currentScreen instanceof Leaderboard) {
            Leaderboard board = (Leaderboard) currentScreen;
            board.updateScores(scoreData);
        }
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

    public void resetToMainMenu() {
        if (networkManager != null) {
            networkManager.disconnect();
        }
        if (gameServer != null) {
            gameServer.stopServer();
            gameServer = null;
        }
        switchScreen(new MainMenu(this));
    }

    public void setGameServer(GameServer gameServer){
        this.gameServer = gameServer;
    }

    public void setCurrentServerIp(String ip) {
        this.serverIp = ip;
    }

    public NetworkManager getNetworkManager() {
        return networkManager;
    }

    public JPanel getCurrentScreen() {
        return currentScreen;
    }
}