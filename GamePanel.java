import java.awt.*;
import javax.swing.*;

public class GamePanel extends JPanel {
    private Image bgImage;
    private JLabel timerLabel;
    private JLabel miniLeaderboard;
    private JLabel diffAndIndex;
    private JLabel targetWord;
    private JTextField inputField;
    private String latestScoreData = "";

    private MainFrame mainFrame;
    private String[] words;
    private int currentIndex = 0;
    private int timeLeft = 15;
    private Timer gameTimer;

    public GamePanel(MainFrame mainFrame, String[] words) {
        this.mainFrame = mainFrame;
        this.words = words;

        try {
            bgImage = new ImageIcon(getClass().getResource("/img/GamePanel_Background.jpg")).getImage();
        } catch (Exception e) {
            System.out.println("cannot load Background.png, use basic");
        }

        setLayout(new BorderLayout(20, 20));
        setBorder(BorderFactory.createEmptyBorder(30, 40, 40, 40));

        TopPanel();
        CenterPanel();
        BottomPanel();

        if (words != null && words.length > 0) {
            updateWordDisplay();
        }

        startTimer();
        SwingUtilities.invokeLater(() -> {
            inputField.requestFocusInWindow();
        });

    }

    private void TopPanel() {
        JPanel topPanel = new JPanel(new BorderLayout());

        miniLeaderboard = new JLabel("<html><b>Leaderboard</b><br>Waiting...</html>");
        miniLeaderboard.setFont(new Font("SansSerif", Font.PLAIN, 16));
        miniLeaderboard.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        timerLabel = new JLabel("15", SwingConstants.CENTER);
        timerLabel.setFont(new Font("SansSerif", Font.BOLD, 48));

        diffAndIndex = new JLabel("<html><center><b>1 / 20</b><br>Easy</center></html>", SwingConstants.CENTER);
        diffAndIndex.setFont(new Font("SansSerif", Font.PLAIN, 16));

        topPanel.add(miniLeaderboard, BorderLayout.WEST);
        topPanel.add(timerLabel, BorderLayout.CENTER);
        topPanel.add(diffAndIndex, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);
    }

    private void CenterPanel() {
        JPanel centerPanel = new JPanel(new GridBagLayout());

        targetWord = new JLabel("", SwingConstants.CENTER);
        targetWord.setFont(new Font("SansSerif", Font.BOLD, 56));

        centerPanel.add(targetWord);
        add(centerPanel, BorderLayout.CENTER);
    }

    private void BottomPanel() {
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setOpaque(false);

        inputField = new JTextField(25);
        inputField.setFont(new Font("SansSerif", Font.PLAIN, 28));
        inputField.setHorizontalAlignment(JTextField.CENTER);
        inputField.addActionListener(e -> checkAndSendAnswer());

        bottomPanel.add(inputField);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void checkAndSendAnswer() {
        String userInput = inputField.getText().trim();
        
        if (currentIndex < words.length && userInput.equalsIgnoreCase(words[currentIndex])) {
            mainFrame.getNetworkManager().sendAnswer(timeLeft);
        }
        else{
            mainFrame.getNetworkManager().sendAnswer(0);
        }
        currentIndex++;
        if (currentIndex < words.length) {
            updateWordDisplay();
            resetTimer();
        } else {
            finishGame();
        }
        
        inputField.setText("");
    }

    private void updateWordDisplay() {
        targetWord.setText(words[currentIndex]);

        String diff = "Easy";
        if (currentIndex >= 5)
            diff = "Medium";
        if (currentIndex >= 10)
            diff = "Hard";
        if (currentIndex >= 15)
            diff = "Very Hard";

        diffAndIndex.setText("<html><center><b>" + (currentIndex + 1) + " / 20</b><br>" + diff + "</center></html>");
    }

    private void startTimer() {
        gameTimer = new Timer(1000, e -> {
            timeLeft--;
            timerLabel.setText(String.valueOf(timeLeft));
            if (timeLeft <= 0) {
                currentIndex++;
                if (currentIndex < words.length) {
                    updateWordDisplay();
                    resetTimer();
                } else {
                    finishGame();
                }
            }
        });
        gameTimer.start();
    }

    private void resetTimer() {
        timeLeft = 15;
        timerLabel.setText("15");
    }

    private void finishGame() {
        targetWord.setText("FINISHED!");
        inputField.setEnabled(false);
        if (gameTimer != null)
            gameTimer.stop();
        
        Timer delayTimer = new Timer(3000, e -> {
            mainFrame.switchScreen(new Leaderboard(mainFrame, latestScoreData));
        });
        delayTimer.setRepeats(false);
        delayTimer.start();
    }

    public void updateLeaderboard(String scoreData) {
        this.latestScoreData = scoreData;
        String formatted = scoreData.replace(",", "<br>").replace(":", " : ");
        miniLeaderboard.setText("<html><b>Leaderboard</b><br>" + formatted + "</html>");

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
            g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
    }
}
