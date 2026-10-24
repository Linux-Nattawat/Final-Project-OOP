import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {
    private MainFrame mainFrame;

    MainMenu(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setTitle("Main Menu");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));

        add(panel, BorderLayout.CENTER);

        JPanel box = new JPanel(new BorderLayout());
        panel.add(box, BorderLayout.CENTER);

        Label word = new Label("Word Running!", Label.CENTER);
        word.setFont(new Font("Arial", Font.BOLD, 30));

        box.add(word, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(2, 2, 10, 10));

        JTextField nameField = new JTextField(20);
        JTextField ipField = new JTextField(20);

        JLabel nameLabel = new JLabel("Input Name");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 25));

        JLabel ipLabel = new JLabel("IP Server");
        ipLabel.setFont(new Font("Arial", Font.BOLD, 25));

        form.add(nameLabel);
        form.add(nameField);
        form.add(ipLabel);
        form.add(ipField);
        form.setPreferredSize(new Dimension(600, 120));

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 0));

        JButton createButton = new JButton("Create");
        createButton.setFont(new Font("Arial", Font.BOLD, 25));
        JButton joinButton = new JButton("Join");
        joinButton.setFont(new Font("Arial", Font.BOLD, 25));

        createButton.setPreferredSize(new Dimension(150, 60));
        joinButton.setPreferredSize(new Dimension(150, 60));

        Label or = new Label("OR");
        or.setFont(new Font("Arial", Font.BOLD, 25));

        buttons.add(createButton);
        buttons.add(or);
        buttons.add(joinButton);

        JPanel group = new JPanel(new BorderLayout(0, 30));
        group.add(form, BorderLayout.NORTH);
        group.add(buttons, BorderLayout.SOUTH);

        JPanel middle = new JPanel();
        middle.setBorder(
        BorderFactory.createEmptyBorder(150, 0, 0, 0));
        middle.add(group);
        box.add(middle, BorderLayout.CENTER);

        joinButton.addActionListener(e -> {
        String name = nameField.getText();
        String ip = ipField.getText();

        });

        createButton.addActionListener(e -> {
            
        });


    }
}