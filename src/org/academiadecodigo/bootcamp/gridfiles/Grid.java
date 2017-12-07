package org.academiadecodigo.bootcamp.gridfiles;

public class Grid {
    public  static final int PADDING = 10;
    public  static final int CELL = 10;
    private int cols;
    private int rows;
    private Cell[][] cell;

    public Grid(int cols, int rows) {
        this.cols = cols;
        this.rows = rows;
    }

    public void createGrid() {
        cell = new Cell[cols][rows];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                cell[col][row] = new Cell(col, row);
            }
        }
    }

    public void paint(int col, int row) {
        cell[col][row].setColor();
    }

    public boolean isPaint(int col, int row) {
        return cell[col][row].isPainted();
    }

    public Cell getCell(int col, int row) {
        return cell[col][row];
    }

    public int getPADDING() {
        return PADDING;
    }

     public int getCELL() {
        return CELL;
    }

    public int getCol() {
        return cols;
    }

    public int getRow() {
        return rows;
    }

}
