import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
public class Leaderboard extends JPanel {
    private MainFrame mainFrame;
    private ArrayList<Player> players;
    private JLabel titleLabel;
    private JPanel rankPanel;
    private JButton restartButton;
    private JButton mainMenuButton;
        
    public Leaderboard(MainFrame mainFrame) {

       this.mainFrame = mainFrame ;

        // setTitle("LeaderBoard");
        // setSize(1000,800);
        // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // setLocationRelativeTo(null);
        setLayout(new BorderLayout ());
        // หัวข้อ
        titleLabel = new JLabel("Leaderboard", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Monospaced", Font.BOLD, 28));
        add(titleLabel, BorderLayout.NORTH);

        // กล่องอันดับ
        rankPanel = new JPanel();
        add(rankPanel, BorderLayout.CENTER);

         // ปุ่ม
        JPanel bottom = new JPanel();
        restartButton = new JButton("Restart");
        mainMenuButton = new JButton("Main Menu");
        bottom.add(restartButton);
        bottom.add(mainMenuButton);
        add(bottom, BorderLayout.SOUTH);
         showRanking();
        setVisible(true);

    }
        private void showRanking() {
        // 1) เรียงคะแนน มาก -> น้อย (bubble sort)
        for (int i = 0; i < players.size() - 1; i++) {
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
    }

}