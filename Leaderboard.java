import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
public class Leaderboard extends JFrame {
    private ArrayList<Player> players;
    private JLabel titleLabel;
    //private JTextArea rankArea;
        
    public Leaderboard(ArrayList<Player> players){
        this.players = players;

        setTitle("LeaderBoard");
        setSize(1000,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout ());
        titleLabel = new JLabel("Leaderboard", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Monospaced", Font.BOLD, 28));
        add(titleLabel, BorderLayout.NORTH);
        //rankArea = new JTextArea();
        //rankArea.setEditable(false);
        //rankArea.setFont(new Font("Monospaced", Font.PLAIN, 20));
        //add(new JScrollPane(rankArea), BorderLayout.CENTER);
        setVisible(true);

    }

}