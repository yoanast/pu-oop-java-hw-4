package Game;

import java.awt.*;

public class Impassible extends Tile{

    public Impassible(int row, int col) {
        super(row, col);
    }

    @Override
    public void renderBoard(Graphics g) {

        int tileX = this.col * this.TILE_SIZE;
        int tileY = this.row * this.TILE_SIZE;
        Color nBlue = new Color (0, 80, 239);

        g.setColor(nBlue);
        g.fillRect(tileX,tileY,TILE_SIZE,TILE_SIZE);

        drawBorders(g,tileX,tileY);
    }

}
