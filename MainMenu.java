import java.awt.*;
import javax.swing.*;

public class MainMenu extends JPanel {
    private MainFrame mainFrame;
    private Image bgImage;

    public MainMenu(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        try {
            bgImage = new ImageIcon(getClass().getResource("/img/MainMenu_Background.jpg")).getImage();
        } catch (Exception e) {
            System.out.println("Could not load Background.png, using fallback gradient.");
        }

        setLayout(new BorderLayout());
        setOpaque(false);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));
        panel.setOpaque(false);
        add(panel, BorderLayout.CENTER);

        JPanel box = new JPanel(new BorderLayout());
        box.setOpaque(false);
        panel.add(box, BorderLayout.CENTER);

        // JLabel word = new JLabel("Word Running!", SwingConstants.CENTER);
        // word.setFont(new Font("Arial", Font.BOLD, 36));
        // box.add(word, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(2, 2, 10, 10));
        form.setOpaque(false);

        JTextField nameField = new JTextField(20);
        JTextField ipField = new JTextField(20);
        ipField.setText("127.0.0.1");

        JLabel nameLabel = new JLabel("Input Name");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 22));

        JLabel ipLabel = new JLabel("IP Server");
        ipLabel.setFont(new Font("Arial", Font.BOLD, 22));

        form.add(nameLabel);
        form.add(nameField);
        form.add(ipLabel);
        form.add(ipField);
        form.setPreferredSize(new Dimension(600, 100));

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 0));
        buttons.setOpaque(false);

        JButton createButton = new JButton("Create");
        createButton.setFont(new Font("Arial", Font.BOLD, 22));
        JButton joinButton = new JButton("Join");
        joinButton.setFont(new Font("Arial", Font.BOLD, 22));

        createButton.setPreferredSize(new Dimension(150, 50));
        joinButton.setPreferredSize(new Dimension(150, 50));

        JLabel or = new JLabel("OR");
        or.setFont(new Font("Arial", Font.BOLD, 22));

        buttons.add(createButton);
        buttons.add(or);
        buttons.add(joinButton);

        JPanel group = new JPanel(new BorderLayout(0, 30));
        group.setOpaque(false);
        group.add(form, BorderLayout.NORTH);
        group.add(buttons, BorderLayout.SOUTH);

        JPanel middle = new JPanel();
        middle.setOpaque(false);
        middle.setBorder(BorderFactory.createEmptyBorder(200, 0, 0, 0));
        middle.add(group);
        box.add(middle, BorderLayout.CENTER);

        joinButton.addActionListener(e -> {
            String name = nameField.getText();
            String ip = ipField.getText();

            Lobby lobby = new Lobby(mainFrame);
            mainFrame.switchScreen(lobby);

            SwingUtilities.invokeLater(() -> {
                mainFrame.getNetworkManager().connect(ip, 12345, name);
            });
        });

        createButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter your name.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            new GameServer();
            boolean connected = mainFrame.getNetworkManager().connect("127.0.0.1", 12345, name);
            if (connected) {
                mainFrame.switchScreen(new Lobby(mainFrame));
            }

        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (bgImage != null) {
            g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
        } else {
            Graphics2D g2d = (Graphics2D) g;
            GradientPaint gp = new GradientPaint(0, 0, new Color(245, 247, 250), 0, getHeight(),
                    new Color(195, 207, 226));
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }
    }
}