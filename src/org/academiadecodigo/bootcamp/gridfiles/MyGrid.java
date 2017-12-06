package org.academiadecodigo.bootcamp.gridfiles;

import org.academiadecodigo.bootcamp.Grid;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyGrid implements Grid {
    public  static final int PADDING = 10;
    public  static final int CELL = 10;
    private int cols;
    private int rows;
    private GridPositions[][] gridPositions;

    public MyGrid(int cols, int rows) {
        this.cols = cols;
        this.rows = rows;
    }




    @Override
    public void createGrid() {
        gridPositions = new GridPositions[cols][rows];
        for (int i = 0; i < rows; i++) {
            for (int y = 0; y < cols; y++) {
                gridPositions[y][i] = new GridPositions(y, i);
            }
        }
    }

    public void positionStates(FileOutputStream savefile) {
        byte[] buffer;
        for (int i = 0; i < rows; i++) {
            for (int y = 0; y < cols; y++) {
                if(gridPositions[y][i].isPainted()) {
                    buffer = "1".getBytes();
                } else {
                    buffer = "0".getBytes();
                }
                try {
                    savefile.write(buffer);
                    if (y == cols -1 ) {
                        savefile.write("\n".getBytes());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void positionLoad(BufferedReader loadbReader) {
        Iterator it = new Iterator() {
            String[] words = new String[0];
            int counter = -1;
            String line = "";
            int numberRow = -1;
            int numberCol = -1;
            public void load(String word, int col, int row) {
                gridPositions[col][row].loadColor(word);
            }

            @Override
            public boolean hasNext() {
                boolean hasNext = true;
                if (counter == words.length) {
                    hasNext = false;
                }
                return hasNext;
            }

            @Override
            public Object next() {

                counter++;
                numberCol++;
                if (!hasNext()) {
                    try {
                        line = loadbReader.readLine();
                        if (line == null) {
                            loadbReader.close();
                            return "";
                        }
                        while (line.equals("")) {
                            line = loadbReader.readLine();
                            if (line == null) {
                                loadbReader.close();
                                return "";
                            }
                        }
                        numberRow++;
                        words = new String[line.split("(?!^)").length];
                        words = line.split("(?!^)");
                        counter = 0;
                        numberCol = 0;
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                }
                load(words[counter],numberCol,numberRow);
                return words[counter];
            }
        };

        while(it.hasNext()) {
            it.next();
        }
    }

    public void paint(int col, int row) {
        gridPositions[col][row].setColor();
    }

    public boolean isPaint(int col, int row) {
        return gridPositions[col][row].isPainted();
    }

     public int getPADDING() {
        return PADDING;
    }

     public int getCELL() {
        return CELL;
    }

    @Override
    public int getCol() {
        return cols;
    }

    @Override
    public int getRow() {
        return rows;
    }

}
