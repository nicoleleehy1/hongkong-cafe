package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class TileManager {

    GamePanel gp;
    Tile[] tile;


    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];
        getTileImage();
    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/floortile_1.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/floortile_2.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/pineapplebun.png"));

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;
        int alter = 1;

        while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
            g2.drawImage(tile[0].image, x, y, gp.tileSize, gp.tileSize, null);
            g2.drawImage(tile[1].image, x + gp.tileSize, y, gp.tileSize, gp.tileSize, null);
            col += 2;
            x += (gp.tileSize * 2);
            if ((col == gp.maxScreenCol || col == (gp.maxScreenCol + 1) )&& alter == 1) {
                col = -1;
                x = -48;
                row++;
                y += gp.tileSize;
                alter = 2;
            } else if ((col == gp.maxScreenCol || col == (gp.maxScreenCol + 1)) && alter == 2) {
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
                alter = 1;
            }
        }
    }
}
