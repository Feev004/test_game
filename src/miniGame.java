import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class miniGame extends JFrame implements KeyListener {
    private JPanel gamePanel;
    private int x, y;
    private int score;
    private JLabel scoreLabel;

    public miniGame() {
        // Set up the JFrame
        setTitle("My Game");
            setSize(400, 400);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize game variables
        x = 200;
        y = 200;
        score = 0;

        // Create the game panel
        gamePanel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLUE);
                g.fillArc(x, y, 50, 50,0,270);
            }
        };
        gamePanel.setBackground(Color.WHITE);
        add(gamePanel, BorderLayout.CENTER);

        // Create the score label
        scoreLabel = new JLabel("Score: " + score);
        add(scoreLabel, BorderLayout.NORTH);

        // Add the key listener
        addKeyListener(this);
        setFocusable(true);
    }

    // KeyListener methods
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            x -= 10; // Move left
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            x += 10; // Move right
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            y -= 10; // Move up
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            y += 10; // Move down
        } else if (e.getKeyCode() == KeyEvent.VK_F1) {//start new game
            newGame();
        }else if (e.getKeyCode() == KeyEvent.VK_F2) {//exit game
            exitGame();
        }

        gamePanel.repaint(); // Redraw the game panel

        // Increase the score when any arrow key is pressed
        score++;
        scoreLabel.setText("Score: " + score);
    }
    public void newGame(){
        score = 0;
    }
    public void exitGame(){
        System.exit(0);
    }
    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        miniGame game = new miniGame();
        game.setVisible(true);
    }
}