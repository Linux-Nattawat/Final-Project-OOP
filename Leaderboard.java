import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.event.*;

public class Leaderboard extends JPanel implements ActionListener {
    private MainFrame mainFrame;
    private ArrayList<Player> players;
    private JLabel titleLabel;
    private JPanel rankPanel;
    private JButton mainMenuButton;
    private Image bgImage;

    public Leaderboard(MainFrame mainFrame, String scoreData) {
        this.mainFrame = mainFrame;
        this.players = new ArrayList<>();

        try {
            bgImage = new ImageIcon(getClass().getResource("/img/Leaderboard_Background.jpg")).getImage();
        } catch (Exception e) {
            System.out.println("cannot load Background.png, use basic");
        }

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(100, 110, 110, 110));

        titleLabel = new JLabel("Leaderboard",JLabel.CENTER);
        titleLabel.setOpaque(true);
        titleLabel.setFont(new Font("Monospaced", Font.BOLD, 28));
        add(titleLabel, BorderLayout.NORTH);

        rankPanel = new JPanel();
        add(rankPanel, BorderLayout.CENTER);

        JPanel bottom = new JPanel();
        mainMenuButton = new JButton("Main Menu");
        bottom.add(mainMenuButton);
        add(bottom, BorderLayout.SOUTH);
        mainMenuButton.addActionListener(e -> {
            mainFrame.resetToMainMenu();
            mainFrame.switchScreen(new MainMenu(mainFrame));
        });

        updateScores(scoreData);
        setVisible(true);

    }

    private void showRanking() {
        for (int i = 0; i < players.size(); i++) {
            for (int j = 0; j < players.size() - 1 - i; j++) {
                if (players.get(j).getScore() < players.get(j + 1).getScore()) {
                    Player temp = players.get(j);
                    players.set(j, players.get(j + 1));
                    players.set(j + 1, temp);
                }
            }
        }
        rankPanel.removeAll();
        rankPanel.setLayout(new GridLayout(10, 1));
        for (int i = 0; i < players.size(); i++) {
            Player p = players.get(i);
            String line = (i + 1) + "  " + p.getName() + "  " + p.getScore();

            JLabel label = new JLabel(line, SwingConstants.CENTER);
            label.setFont(new Font("Monospaced", Font.PLAIN, 28));
            rankPanel.add(label);
        }
        rankPanel.revalidate();
        rankPanel.repaint();
    }

    public void updateScores(String scoreData) {
        if (scoreData == null || scoreData.isEmpty())
            return;
        players.clear();

        String[] parts = scoreData.split(",");
        for (String part : parts) {
            String[] info = part.split(":");
            if (info.length == 2) {
                Player p = new Player(info[0]);
                p.setScore(Integer.parseInt(info[1]));
                players.add(p);
            }
        }
        showRanking();
        revalidate();
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (bgImage != null) {
            g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
        } else {
            Graphics2D g2d = (Graphics2D) g;
            GradientPaint gp = new GradientPaint(0, 0, new Color(200, 220, 240), 0, getHeight(),
                    new Color(150, 180, 220));
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }
    }

}