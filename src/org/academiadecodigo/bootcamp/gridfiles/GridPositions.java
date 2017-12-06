package org.academiadecodigo.bootcamp.gridfiles;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class GridPositions {

    private boolean painted;
    private int col;
    private int row;
    private Rectangle cell;

    public GridPositions(int col, int row) {
        painted = false;
        this.col = col;
        this.row = row;
        cell = new Rectangle(col * MyGrid.CELL + MyGrid.PADDING, row * MyGrid.CELL + MyGrid.PADDING, MyGrid.CELL, MyGrid.CELL);
        cell.draw();
    }

    public void setColor() {
        if (!painted) {
            cell.delete();
            cell.setColor(Color.BLACK);
            cell.fill();
            painted = true;
        } else {
            cell.delete();
            cell.draw();
            painted = false;
        }
    }

    public void loadColor(String tint) {
        if (tint.equals("1")) {
            cell.delete();
            cell.setColor(Color.BLACK);
            cell.fill();
            painted = true;
        } else {
            cell.delete();
            cell.draw();
            painted = false;
        }
    }

    public boolean isPainted() {
        return painted;
    }
}
