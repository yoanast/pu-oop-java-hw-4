package Game;

import java.awt.*;

public abstract class Tile {

    protected int row;
    protected int col;
    protected Color color;
    protected String id;
    public static final int TILE_SIZE = 70;

    public static Color nYellow = new Color(254, 241, 203);
    public static Color nGreen = new Color(213, 232, 212);
    public static Color nPink = new Color(248, 206, 204);
    public static Color nBlue = new Color (0, 80, 239);

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

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getId() {
        return id;
    }

}
