package Game;

import java.awt.*;

public abstract class Tile {

    protected int row;
    protected int col;
    public static final int TILE_SIZE = 70;

    /**
     *  Конструктор за полетата.
     */
    public Tile (int row, int col) {

        this.row = row;
        this.col = col;
    }


    /**
     *  Метод, чрез който визуализираме отделните полета на дъската.
     */
    public abstract void renderBoard(Graphics g);


    /**
     *  Метод, чрез който визуализираме рамка на полетата.
     */
    public void drawBorders(Graphics g, int tileX, int tileY) {

        g.setColor(Color.black);
        g.drawRect(tileX,tileY,TILE_SIZE,TILE_SIZE);
    }

}
