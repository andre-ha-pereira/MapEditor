package org.academiadecodigo.bootcamp;

import org.academiadecodigo.bootcamp.gridfiles.Grid;
import java.io.*;

public class FileManager {

    private Grid grid;

    public FileManager(Grid grid) {
        this.grid = grid;
    }

    public void saveState() {
        FileOutputStream savefile = null;
        try {
            savefile = new FileOutputStream("resources/save.txt");
            positionStates(savefile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (savefile != null) {
                try {
                    savefile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void loadState() {
        BufferedReader loadbReader = null;
        try {
            loadbReader = new BufferedReader(new FileReader("resources/save.txt"));
            positionLoad(loadbReader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (loadbReader != null) {
                try {
                    loadbReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //CHECK WHICH CELLS ARE PAINTED
    private void positionStates(FileOutputStream savefile) {
        byte[] buffer;
        for (int i = 0; i < grid.getRow(); i++) {
            for (int y = 0; y < grid.getCol(); y++) {
                if (grid.getCell(y, i).isPainted()) {
                    buffer = "1".getBytes();
                } else {
                    buffer = "0".getBytes();
                }
                try {
                    savefile.write(buffer);
                    if (y == grid.getCol() - 1) {
                        savefile.write("\n".getBytes());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void positionLoad(BufferedReader loadbReader) {
        String result = "";
        String[] resultSplit;
        String line;
        int resultPosition = 0;
        try {
            while ((line = loadbReader.readLine()) != null) {
                result += line;
            }
            resultSplit = result.split("(?!^)");
            for (int i = 0; i < grid.getRow(); i++) {
                for(int y = 0; y < grid.getCol(); y++) {
                        grid.getCell(y,i).loadColor(resultSplit[resultPosition]);
                        resultPosition++;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}

