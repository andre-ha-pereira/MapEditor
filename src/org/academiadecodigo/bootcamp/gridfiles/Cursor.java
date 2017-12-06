package org.academiadecodigo.bootcamp.gridfiles;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;


public class Cursor {
    private Rectangle myPosition;
    private int col;
    private int row;
    private MyGrid myGrid;

    public Cursor(MyGrid myGrid) {
        this.col = 0;
        this.row = 0;
        this.myGrid = myGrid;
    }

    public void move(MoveDirection direction) {
        switch (direction) {
            case UP:
                moveUp();
            break;
            case DOWN:
                moveDown();
            break;
            case LEFT:
                moveLeft();
            break;
            case RIGHT:
                moveRight();
            break;
        }
        positionColor();
        System.out.println("Col: " + col);
        System.out.println("Row: " + row);
    }

    private void moveRight() {
        if (col == myGrid.getCol() - 1) {
            return;
        }
        col++;
        positionDelete();
        myPosition.translate(1* myGrid.getCELL(), 0);
        positionDraw();
    }

    private void moveLeft() {
        if (col == 0) {
            return;
        }
        col--;
        positionDelete();
        myPosition.translate(-1* myGrid.getCELL(), 0);
        positionDraw();
    }

    private void moveUp() {
        if (row == 0) {
            return;
        }
        row--;
        positionDelete();
        myPosition.translate(0, -1 * myGrid.getCELL());
        positionDraw();
    }

    private void moveDown() {
        if (row == myGrid.getRow() - 1) {
            return;
        }
        row++;
        myPosition.translate(0, myGrid.getCELL());
    }

    public void positionColor() {
        if(myGrid.isPaint(col,row)) {
            positionDelete();
            myPosition.setColor(Color.GREEN);
            positionDraw();
        } else {
            positionDelete();
            myPosition.setColor(Color.RED);
            positionDraw();
        }
    }

    public void createMyPosition() {
        myPosition = new Rectangle(positionCol(),positionRow(), myGrid.getCELL(),myGrid.getCELL());
        myPosition.setColor(Color.RED);
        positionDraw();
    }

    public void positionDraw() {
        myPosition.fill();
    }

    public void positionDelete() {
        myPosition.delete();
    }

    public int positionCol() {
        return col * myGrid.getCELL() + myGrid.getPADDING();
    }

    public int positionRow() {
        return row * myGrid.getCELL() + myGrid.getPADDING();
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }
}
