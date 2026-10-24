import javax.swing.*;
import java.awt.*;
public class MainMenu extends JFrame {
    MainMenu() {
        setTitle("Main Menu");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Word Running!", JLabel.CENTER);
        add(title, BorderLayout.NORTH);
    }
}