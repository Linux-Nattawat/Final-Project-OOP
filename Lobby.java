import java.awt.*;
import javax.swing.*;

public class Lobby extends JPanel {
    private MainFrame mainFrame;
    private JLabel playerCountLabel;
    private JLabel ipLabel;
    private JTextArea playerListArea;
    private Image bgImage;

    public Lobby(MainFrame mainFrame,String serverIp) {
        this.mainFrame = mainFrame;

        try {
            bgImage = new ImageIcon(getClass().getResource("/img/Lobby_Background.jpg")).getImage();
        } catch (Exception e) {
            System.out.println("cannot load Background.png, use basic");
        }

        setLayout(new BorderLayout(20, 20));
        setBorder(BorderFactory.createEmptyBorder(40, 80, 40, 80));
        setOpaque(false);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);

        playerCountLabel = new JLabel("Player : 1 คน");
        playerCountLabel.setFont(new Font("Tahoma", Font.BOLD, 22));

        ipLabel = new JLabel("IP :"+ serverIp, SwingConstants.RIGHT);
        ipLabel.setFont(new Font("Tahoma", Font.BOLD, 22));

        topPanel.add(playerCountLabel, BorderLayout.WEST);
        topPanel.add(ipLabel, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        playerListArea = new JTextArea();
        playerListArea.setFont(new Font("Tahoma", Font.PLAIN, 24));
        playerListArea.setEditable(false);
        playerListArea.setMargin(new Insets(15, 20, 15, 20));

        JScrollPane scrollPane = new JScrollPane(playerListArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        scrollPane.getViewport().setOpaque(false);
        playerListArea.setOpaque(false);
        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setOpaque(false);

        JButton startBtn = new JButton("Start!");
        startBtn.setFont(new Font("Tahoma", Font.BOLD, 22));
        startBtn.setPreferredSize(new Dimension(160, 50));
        
        startBtn.addActionListener(e -> {
            mainFrame.getNetworkManager().sendMessage("START_GAME");
        });

        bottomPanel.add(startBtn);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public void updateLobby(String[] players) {
        playerCountLabel.setText("Player : " + players.length + " คน");
        
        StringBuilder sb = new StringBuilder();
        for (String p : players) {
            sb.append(p).append("\n");
        }
        playerListArea.setText(sb.toString());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
            g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
    }
}