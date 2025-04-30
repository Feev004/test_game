import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class game extends JFrame implements KeyListener {
    private JPanel gamePanel;
    private int x, y; // ตำแหน่งของผู้เล่น
    private int c = 0;
    private int score;
    private JLabel scoreLabel;
    private int foodX, foodY;
    private Random rand = new Random();

    public game() {
        // ตั้งค่า JFrame
        setTitle("Fun Game");
        setSize(640, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // เริ่มเกม
        x = 200;
        y = 200;
        score = 0;
        generateFood();

        // พื้นที่เกม
        gamePanel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);

                // วาดผู้เล่น
                g.setColor(Color.BLUE);
                if (c == 1)
                    c = 225;
                if (c == 2)
                    c = 55;
                if (c == 3)
                    c = 135;
                if (c == 4)
                    c = 315;
                g.fillArc(x, y, 50, 50, c, 270);

                // วาด hitbox ของผู้เล่น
                g.setColor(Color.GREEN);
                g.drawRect(x, y, 50, 50);
                g.setColor(Color.red);
                g.drawRect(x + 20, y + 20, 10, 10);

                g.setColor(Color.black);
                g.drawRect(x, y, 10, 10);

                // วาดอาหาร
                g.setColor(Color.RED);
                g.fillOval(foodX, foodY, 30, 30);

                // วาด hitbox ของอาหาร
                g.setColor(Color.ORANGE);
                g.drawRect(foodX, foodY, 10, 10);
            }

            // @Override
            // public void paintComponent(Graphics g) {
            //     super.paintComponent(g);

            //     // วาดผู้เล่น
            //     g.setColor(Color.BLUE);
            //     if (c == 1)
            //         c = 225;
            //     if (c == 2)
            //         c = 55;
            //     if (c == 3)
            //         c = 135;
            //     if (c == 4)
            //         c = 315;
            //     g.fillArc(x, y, 50, 50, c, 270);

            //     // วาดอาหาร
            //     g.setColor(Color.RED);
            //     g.fillOval(foodX, foodY, 10, 10);
            // }
        };
        gamePanel.setBackground(Color.WHITE);
        add(gamePanel, BorderLayout.CENTER);

        // ป้ายคะแนน
        scoreLabel = new JLabel("Score: " + score + "    [F1: New Game]  [F2: Exit]");
        add(scoreLabel, BorderLayout.NORTH);

        // Key listener
        addKeyListener(this);
        setFocusable(true);
        setVisible(true);
    }

    // กดปุ่มควบคุม
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                x -= 5;
                c = 1;
                break;
            case KeyEvent.VK_RIGHT:
                x += 5;
                c = 2;
                break;
            case KeyEvent.VK_UP:
                y -= 5;
                c = 3;
                break;
            case KeyEvent.VK_DOWN:
                y += 5;
                c = 4;
                break;
            case KeyEvent.VK_F1:
                newGame();
                break;
            case KeyEvent.VK_F2:
                exitGame();
                break;
        }

        // ตรวจสอบการชนอาหาร
        if (Math.abs(x - foodX) < 50 && Math.abs(y - foodY) < 50) {
            score += 10;
            generateFood();
        }

        gamePanel.repaint();
        scoreLabel.setText("Score: " + score + "    [F1: New Game]  [F2: Exit]");
    }

    // สุ่มตำแหน่งอาหาร
    private void generateFood() {
        foodX = rand.nextInt(590);
        foodY = rand.nextInt(470);
    }

    // รีเซ็ตเกม
    public void newGame() {
        score = 0;
        x = 200;
        y = 200;
        generateFood();
        scoreLabel.setText("Score: " + score + "    [F1: New Game]  [F2: Exit]");
        gamePanel.repaint();
    }

    // ออกจากเกม
    public void exitGame() {
        JOptionPane.showMessageDialog(this, "Total score = " + score);
        System.exit(0);
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public static void main(String[] args) {
        new game();
    }
}
