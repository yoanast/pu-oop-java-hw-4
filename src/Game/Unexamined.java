package Game;

import java.awt.*;

public class Unexamined extends Tile{

    public Unexamined(int row, int col) {

        super(row, col);
        this.color = nPink;
        this.id = "Unexamined";
    }

    @Override
    public void renderBoard(Graphics g) {

        int tileX = this.col * this.TILE_SIZE;
        int tileY = this.row * this.TILE_SIZE;

        g.setColor(this.color);
        g.fillRect(tileX,tileY,TILE_SIZE,TILE_SIZE);

        drawBorders(g,tileX,tileY);
    }

}
