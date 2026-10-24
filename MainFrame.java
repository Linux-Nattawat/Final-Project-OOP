import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class MainFrame extends JFrame {
    public MainFrame(){
        setTitle("Word Running");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        switchScreen(new MainMenu(this));
    }

    public void switchScreen(JPanel newScreen) {
        getContentPane().removeAll();
        getContentPane().add(newScreen);
        getContentPane().revalidate();
        getContentPane().repaint();
    }
    
}
