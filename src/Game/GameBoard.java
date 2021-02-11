package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class GameBoard extends JFrame implements MouseListener {

    public static int firstRandomNumber;
    public static int secondRandomNumber;
    Player player = new Player(firstRandomNumber, secondRandomNumber);
    BabaYaga babaYaga = new BabaYaga(firstRandomNumber, secondRandomNumber);
    public Object[][] tileCollection;
    public Object selectedTile;

    public GameBoard() {

        this.setSize(600, 600);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.addMouseListener(this);
        this.tileCollection = new Object[8][8];
        fillTileCollection();
    }

    /**
     *  Метод, който прихваща натискане на бутона на мишката.
     */
    @Override
    public void mouseClicked(MouseEvent e) {

        int row = this.getDimensionsBasedOnCoordinates(e.getY());
        int col = this.getDimensionsBasedOnCoordinates(e.getX());
        Tile tile = (Tile) this.getTile(row, col);

        if (tile.getId() != "Impassible" && tile.getColor() != Tile.nBlue && selectedTile == null) {
            this.selectedTile = this.getTile(row, col);
            tile.setColor(Tile.nYellow);
            repaint();
        } else if (this.selectedTile != null && tile.getId() == "GPS") {
            movePlayer(row, col);
            hasPlayerFoundBabaYaga();
            updateBoard(row,col);
        } else if ( tile.getColor() != Tile.nBlue || tile.getId() != "Impassible" && this.selectedTile != null) {
            tile.setColor(getNewColor());
            if (tile.getColor() == Tile.nYellow) {
                movePlayer(row, col);
            } else {
                JOptionPane.showMessageDialog(null, "Невъзможно преместване, защото се падна синьо квадратче!",
                        "Греда", JOptionPane.WARNING_MESSAGE);
            }
            updateBoard(row, col);
        } else {
            JOptionPane.showMessageDialog(null,"Невъзможно преместване върху синьо квадратче!",
                    "Грешка", JOptionPane.WARNING_MESSAGE);

            }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

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
     *  Метод, чрез който визуализираме играча.
     */
    public void renderPlayer(int row, int col, Graphics g) {

        if (this.isThereTile(row, col)) {
            Tile t = (Tile)this.getTile(row, col);
            t.getColor();
            if(t.getColor() == Tile.nYellow) {
                player.render(g);
            }
        }
    }

    public void renderBabaYaga(int row, int col, Graphics g) {
        if (this.isThereTile(row, col)) {
            Tile t = (Tile)this.getTile(row, col);
            t.getColor();
            if(t.getColor() == Tile.nGreen) {
                babaYaga.render(g);
            }
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
                this.renderPlayer(row, col, g);
                this.renderBabaYaga(row, col, g);
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
     *  Метод, чрез който определяме оцветяването на избраното поле (80% за жълто, 20% за синьо).
     */
    public static Color getNewColor() {

        Random rand = new Random();
        int randomN = rand.nextInt(4);
        if ( randomN == 0 ) {
            return Tile.nBlue;
        } else return Tile.nYellow;
    }

    /**
     *  Метод, чрез който пълним колекцията на дъската с обекти.
     */
    public void fillTileCollection() {

        getRandomStartPosition();
        this.tileCollection[firstRandomNumber][secondRandomNumber] =
                (new Start(firstRandomNumber,secondRandomNumber));

        player.setRow(firstRandomNumber);
        player.setCol(secondRandomNumber);

        for(int i = 0; i < 8; i++) {
            getRandomNumber();
            if(!this.isThereTile(firstRandomNumber, secondRandomNumber)) {
                this.tileCollection[firstRandomNumber][secondRandomNumber] =
                        (new GPS(firstRandomNumber, secondRandomNumber));
            } else i--;
        }

        babaYaga.setRow(firstRandomNumber);
        babaYaga.setCol(secondRandomNumber);

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

    public int getDimensionsBasedOnCoordinates(int coordinates) {
        return coordinates / Tile.TILE_SIZE;
    }

    /**
     *  Метод, чрез който задаваме правилата на движение на играча.
     */
    public void movePlayer(int row, int col) {
        if(row == player.getRow() + 1 && col == player.getCol()) {
            player.move(row,col);
        } else if (row == player.getRow() && col == player.getCol() + 1) {
            player.move(row,col);
        } else if (row == player.getRow() && col == player.getCol() - 1) {
            player.move(row,col);
        } else if (row == player.getRow() - 1&& col == player.getCol() ) {
            player.move(row, col);
        } else {
            JOptionPane.showMessageDialog(null,"Възможно е преместване само по 1 квадратче!",
                    "Грешка", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     *  Метод, чрез който опресняваме позицията на играча и визуализацията на дъската.
     */
    public void updateBoard(int row, int col) {

        this.tileCollection[row][col] = this.selectedTile;
        this.selectedTile = null;
        this.repaint();
    }

    /**
     *  Метод, чрез който проверяваме дали играча е стигнал до координатите на баба Яга.
     */
    public void hasPlayerFoundBabaYaga() {

        if(player.getRow() == babaYaga.getRow() && player.getCol() == babaYaga.getCol()) {
            int response = JOptionPane.showConfirmDialog(null, "Честито! Ти откри баба Яга и спечели играта!" +
                            "Искаш ли да играеш отново?", "Край", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (response == JOptionPane.NO_OPTION) {
                System.exit(0);
            } else if (response == JOptionPane.YES_OPTION) {

            } else if (response == JOptionPane.CLOSED_OPTION) {
                System.exit(0);
            }
        } else {
            JOptionPane.showMessageDialog(null,"За съжаление баба Яга не е тук, продължи да търсиш!",
                    "Грешка", JOptionPane.WARNING_MESSAGE);
        }
    }

}
