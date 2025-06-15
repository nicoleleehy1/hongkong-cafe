package main;

import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    // works as a Game Screen //
    // SCREEN SETTINGS
    final int originalTileSize = 16;
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16; // horizontal
    public final int maxScreenRow = 12; // vertical
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    // FPS
    int fps = 60;

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyH);


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // better rendering performance
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this); // instantiate a thread by pasisng in constructor
        gameThread.start();
    }

    /**
     * Runs this operation.
     */
    // GAME LOOP
    @Override
    public void run() {
        while (gameThread != null) {
            // long currentTime = System.nanoTime();
            double drawInterval = 1000000000 / fps; // 1 second divided by FPS
            double nextDrawTime = System.nanoTime() + drawInterval;
            // 0.01666 seconds to draw one frame

            // 1 UPDATE: update information such as character positions
            update();

            // 2 DRAW: draw screen with updated information
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime); // sleep only accepts milliseconds

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }

    public void update() { // update position
        player.update();
    }

    // standard way to draw things on JPanel
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        tileM.draw(g2);
        player.draw(g2);
        g2.dispose();

    }
}
