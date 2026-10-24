import java.awt.*;
import javax.swing.*;

public class Lobby extends JFrame {

    private int players = 2; 

    public Lobby() {
        
        setTitle("Word Running - Lobby");
        setSize(1000, 800);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // จัดหน้าต่างให้อยู่กึ่งกลางจอ

        //จำนวน
        JLabel playerCountLabel = new JLabel("Player : " + players + " คน");
        playerCountLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        playerCountLabel.setBounds(100, 40, 200, 30);
        add(playerCountLabel);

        //IP 
        JLabel ipLabel = new JLabel("IP : 10.125.15.56", SwingConstants.CENTER);
        ipLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
        ipLabel.setBounds(350, 40, 300, 30);
        add(ipLabel);

        //ผู้เล่น
        JTextArea playerListArea = new JTextArea("Poori\nMokk\n");
        playerListArea.setFont(new Font("Tahoma", Font.PLAIN, 24));
        playerListArea.setEditable(false);
        // เพิ่มระยะห่างจากขอบภายใน (บน: 15, ซ้าย: 20, ล่าง: 15, ขวา: 20)
        playerListArea.setMargin(new Insets(15, 20, 15, 20));

        JScrollPane scrollPane = new JScrollPane(playerListArea);
        scrollPane.setBounds(100, 90, 800, 520);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        add(scrollPane);

        //ปุ่มเริ่ม
        JButton startBtn = new JButton("Start!");
        startBtn.setFont(new Font("Tahoma", Font.BOLD, 22));
        startBtn.setBounds(420, 640, 160, 50);
        add(startBtn);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Lobby());
    }
}