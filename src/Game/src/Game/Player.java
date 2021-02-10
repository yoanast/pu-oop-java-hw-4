package Game;

import java.awt.*;

public class Player {

    public int row;
    public int col;

    public Player(int row, int col) {
        this.row = row;
        this.col = col;

    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void render(Graphics g) {
        int tileX = this.col * Tile.TILE_SIZE;
        int tileY = this.row * Tile.TILE_SIZE;

        g.setColor(Color.black);
        g.drawString(" P ", tileX + 35, tileY + 35);
    }

    public void move(int row, int col) {
        this.row = row;
        this.col = col;
    }
}
