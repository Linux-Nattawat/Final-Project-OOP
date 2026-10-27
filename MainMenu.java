import java.awt.*;
import javax.swing.*;
import java.net.*;

public class MainMenu extends JPanel {
    private MainFrame mainFrame;
    private Image bgImage;

    public MainMenu(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        try {
            bgImage = new ImageIcon(getClass().getResource("/img/MainMenu_Background.jpg")).getImage();
        } catch (Exception e) {
            System.out.println("cannot load Background.png, use basic");
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
            String name = nameField.getText().trim();
            String ip = ipField.getText().trim();

            if (name.isEmpty() || ip.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter name and IP address", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean connected = mainFrame.getNetworkManager().connect(ip, 12345, name);

            if (connected) {
                mainFrame.setCurrentServerIp(ip);
                mainFrame.switchScreen(new Lobby(mainFrame, ip));
            } else {
                JOptionPane.showMessageDialog(this,
                        "Cannot connect to server",
                        "Connection Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        createButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter your name", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            mainFrame.resetToMainMenu();

            GameServer gameServer = new GameServer();
            mainFrame.setGameServer(gameServer);

            String ip = "127.0.0.1";
            try {
                String address = InetAddress.getLocalHost().getHostAddress();
                ip = address;
            } catch (Exception ex) {
                System.out.println("cannot get local IP, use 127.0.0.1");
            }

            mainFrame.setCurrentServerIp(ip);
            boolean connected = mainFrame.getNetworkManager().connect(ip, 12345, name);
            if (connected) {
                mainFrame.switchScreen(new Lobby(mainFrame, ip));
            } else {
                JOptionPane.showMessageDialog(this, "Failed to start server", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
            g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
    }
}