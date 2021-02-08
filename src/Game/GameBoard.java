package Game;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Random;

public class GameBoard extends JFrame {

    public static int firstRandomNumber;
    public static int secondRandomNumber;
    public Object[][] tileCollection;

    public GameBoard() {

        this.setSize(600, 600);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.tileCollection = new Object[8][8];
        fillTileCollection();
    }

    /**
     *  Метод, чрез който визуализираме полетата.
     */
    public void renderTiles(Graphics g, int row, int col) {

         if (this.isThereTile(row, col)) {
            Tile t = (Tile)this.getTile(row, col);
            t.renderBoard(g);
        }
    }

    /**
     *  Метод, чрез който изчертаваме игралната дъска и нейните елементи.
     */
    public void paint(Graphics g) {
        super.paint(g);

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {

                this.renderTiles(g, row, col);
            }
        }
    }

    /**
     *  Метод, който ни връща обект от колекцията по зададени ред и колона.
     */
    public Object getTile(int row, int col) {
       return this.tileCollection[row][col];
    }

    /**
     *  Метод, който ни връща дали на дадени ред и колона вече има поле.
     */
    public boolean isThereTile(int row, int col) {
        return this.tileCollection[row][col] != null;
    }

    /**
     *  Метод, чрез който получаваме две случайни числа и ги записваме на променливите.
     */
    public static void getRandomNumber() {

        Random random = new Random();
        firstRandomNumber = random.nextInt(8);
        secondRandomNumber = random.nextInt(8);

    }

    public static void getRandomStartPosition() {
        Random rand = new Random();
        firstRandomNumber = rand.nextInt(8);
        secondRandomNumber = rand.nextInt(8);
        while (firstRandomNumber != 0 && firstRandomNumber != 7 ||
                secondRandomNumber != 0 && secondRandomNumber != 7) {
            firstRandomNumber = rand.nextInt(8);
            secondRandomNumber = rand.nextInt(8);

        }
    }

    /**
     *  Метод, чрез който пълним игралната дъска.
     */
    public void fillTileCollection() {

        getRandomStartPosition();
        this.tileCollection[firstRandomNumber][secondRandomNumber] =
                (new Start(firstRandomNumber,secondRandomNumber));

        for(int i = 0; i < 8; i++) {
            getRandomNumber();
            if(!this.isThereTile(firstRandomNumber, secondRandomNumber)) {
                this.tileCollection[firstRandomNumber][secondRandomNumber] =
                        (new GPS(firstRandomNumber, secondRandomNumber));
            } else i--;
        }

        for(int i = 0; i < 5; i++) {
            getRandomNumber();
            if(!this.isThereTile(firstRandomNumber, secondRandomNumber)) {
                this.tileCollection[firstRandomNumber][secondRandomNumber] =
                        (new Impassible(firstRandomNumber, secondRandomNumber));
            } else i--;
        }

        for(int i = 0; i < 50; i++) {
            getRandomNumber();
            if(!this.isThereTile(firstRandomNumber, secondRandomNumber)) {
                this.tileCollection[firstRandomNumber][secondRandomNumber] =
                        (new Unexamined(firstRandomNumber, secondRandomNumber));
            } else i--;
        }
    }

}
