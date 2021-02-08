package Game;

import java.awt.*;

public class Unexamined extends Tile{

    public Unexamined(int row, int col) {
        super(row, col);
    }

    @Override
    public void renderBoard(Graphics g) {

        int tileX = this.col * this.TILE_SIZE;
        int tileY = this.row * this.TILE_SIZE;
        Color nPink = new Color(248, 206, 204);

        g.setColor(nPink);
        g.fillRect(tileX,tileY,TILE_SIZE,TILE_SIZE);

        drawBorders(g,tileX,tileY);
    }
}
