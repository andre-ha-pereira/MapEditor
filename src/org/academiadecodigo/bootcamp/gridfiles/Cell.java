package org.academiadecodigo.bootcamp.gridfiles;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Cell {

    private boolean painted;
    private Rectangle cell;

    public Cell(int col, int row) {
        painted = false;
        cell = new Rectangle(col * Grid.CELL + Grid.PADDING, row * Grid.CELL + Grid.PADDING, Grid.CELL, Grid.CELL);
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
            cell.fill();
            painted = true;
        } else {
            cell.draw();
            painted = false;
        }
    }

    public boolean isPainted() {
        return painted;
    }
}
