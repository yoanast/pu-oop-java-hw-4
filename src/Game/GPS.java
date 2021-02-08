package Game;

import java.awt.*;

public class GPS extends Tile{


    public GPS(int row, int col) {
        super(row, col);
    }

    @Override
    public void renderBoard(Graphics g) {

        int tileX = this.col * this.TILE_SIZE;
        int tileY = this.row * this.TILE_SIZE;
        Color nGreen = new Color(213, 232, 212);

        g.setColor(nGreen);
        g.fillRect(tileX,tileY,TILE_SIZE,TILE_SIZE);

        drawBorders(g,tileX,tileY);
    }

}
