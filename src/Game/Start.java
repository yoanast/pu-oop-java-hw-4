package Game;

import java.awt.*;

public class Start extends Tile{

    public Start(int row, int col) {
        super(row, col);
    }

    @Override
    public void renderBoard(Graphics g) {

        int tileX = this.col * this.TILE_SIZE;
        int tileY = this.row * this.TILE_SIZE;
        Color nYellow = new Color(254, 241, 203);

        g.setColor(nYellow);
        g.fillRect(tileX, tileY, TILE_SIZE, TILE_SIZE);

        drawBorders(g,tileX,tileY);
    }

}
