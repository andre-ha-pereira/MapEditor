package org.academiadecodigo.bootcamp.gridfiles;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;


public class Cursor {
    private Rectangle myPosition;
    private int col;
    private int row;
    private Grid grid;

    public Cursor(Grid grid) {
        this.col = 0;
        this.row = 0;
        this.grid = grid;
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
        if (col == grid.getCol() - 1) {
            System.out.println(grid.getCol());
            myPosition.translate(-(grid.getCol()-1) * grid.getCELL(),0);
            col = 0;
            return;
        }
        col++;
        positionDelete();
        myPosition.translate( grid.getCELL(), 0);
        positionDraw();
    }

    private void moveLeft() {
        if (col == 0) {
            myPosition.translate((grid.getCol()-1) * grid.getCELL(),0);
            col = grid.getCol() - 1;
            return;
        }
        col--;
        positionDelete();
        myPosition.translate(-grid.getCELL(), 0);
        positionDraw();
    }

    private void moveUp() {
        if (row == 0) {
            myPosition.translate(0, (grid.getRow()-1) * grid.getCELL());
            row = grid.getRow() - 1;
            return;
        }
        row--;
        positionDelete();
        myPosition.translate(0, -grid.getCELL());
        positionDraw();
    }

    private void moveDown() {
        if (row == grid.getRow() - 1) {
            myPosition.translate(0,-(grid.getRow()-1) * grid.getCELL());
            row = 0;
            return;
        }
        row++;
        myPosition.translate(0, grid.getCELL());
    }

    public void positionColor() {
        if(grid.isPaint(col,row)) {
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
        myPosition = new Rectangle(positionCol(),positionRow(), grid.getCELL(), grid.getCELL());
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
        return col * grid.getCELL() + grid.getPADDING();
    }

    public int positionRow() {
        return row * grid.getCELL() + grid.getPADDING();
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }
}
