package org.academiadecodigo.bootcamp;


import org.academiadecodigo.bootcamp.gridfiles.Cursor;
import org.academiadecodigo.bootcamp.gridfiles.MoveDirection;
import org.academiadecodigo.bootcamp.gridfiles.MyGrid;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

import java.io.*;

public class MapEditor implements KeyboardHandler{

    private MyGrid myGrid;
    private Cursor myPosition;

    public MapEditor(int cols, int rows) {
        myGrid = new MyGrid(cols,rows);
        myGrid.createGrid();
        myPosition = new Cursor(myGrid);
        myPosition.createMyPosition();
        keyboardInit();
    }

    public void keyboardInit() {
        Keyboard keyboard = new Keyboard(this);
        pressUpEvent(keyboard);
        pressDownEvent(keyboard);
        pressLeftEvent(keyboard);
        pressRightEvent(keyboard);
        pressSpaceEvent(keyboard);
        pressSEvent(keyboard);
        pressLEvent(keyboard);
    }

    private void pressUpEvent(Keyboard keyboard) {
        KeyboardEvent pressUp = new KeyboardEvent();
        pressUp.setKey(KeyboardEvent.KEY_UP);
        pressUp.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(pressUp);
    }

    private void pressDownEvent(Keyboard keyboard) {
        KeyboardEvent pressDown = new KeyboardEvent();
        pressDown.setKey(KeyboardEvent.KEY_DOWN);
        pressDown.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(pressDown);
    }

    private void pressLeftEvent(Keyboard keyboard) {
        KeyboardEvent pressLeft = new KeyboardEvent();
        pressLeft.setKey(KeyboardEvent.KEY_LEFT);
        pressLeft.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(pressLeft);
    }

    private void pressRightEvent(Keyboard keyboard) {
        KeyboardEvent pressRight = new KeyboardEvent();
        pressRight.setKey(KeyboardEvent.KEY_RIGHT);
        pressRight.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(pressRight);
    }

    private void pressSpaceEvent(Keyboard keyboard) {
        KeyboardEvent pressSpace = new KeyboardEvent();
        pressSpace.setKey(KeyboardEvent.KEY_SPACE);
        pressSpace.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        keyboard.addEventListener(pressSpace);
    }
    //SAVE BUTTON is S
    private void pressSEvent(Keyboard keyboard) {
        KeyboardEvent pressS = new KeyboardEvent();
        pressS.setKey(KeyboardEvent.KEY_S);
        pressS.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        keyboard.addEventListener(pressS);
    }
    //LOAD BUTTON IS L
    private void pressLEvent(Keyboard keyboard) {
        KeyboardEvent pressL = new KeyboardEvent();
        pressL.setKey(KeyboardEvent.KEY_L);
        pressL.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        keyboard.addEventListener(pressL);
    }

    private void whichDirectionPressed(KeyboardEvent e) {
        if (e.getKey() == KeyboardEvent.KEY_LEFT) {
           myPosition.move(MoveDirection.LEFT);
        }
        if(e.getKey() == KeyboardEvent.KEY_RIGHT) {
            myPosition.move(MoveDirection.RIGHT);
        }
        if (e.getKey() == KeyboardEvent.KEY_UP) {
            myPosition.move(MoveDirection.UP);
        }
        if (e.getKey() == KeyboardEvent.KEY_DOWN) {
            myPosition.move(MoveDirection.DOWN);
        }

    }

    private void whichActionPressed(KeyboardEvent e) {
        if (e.getKey() == KeyboardEvent.KEY_SPACE) {
            paintCell();
        }
        if (e.getKey() == KeyboardEvent.KEY_S) {
            saveState();
        }
        if (e.getKey() == KeyboardEvent.KEY_L) {
            loadState();
        }
    }

    private void loadState() {
        BufferedReader loadbReader = null;
        try {
            loadbReader = new BufferedReader(new FileReader("resources/save.txt"));
            myGrid.positionLoad(loadbReader);
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

    private void paintCell() {
            myGrid.paint(myPosition.getCol(), myPosition.getRow());
            myPosition.positionColor();
    }

    private void saveState() {
        FileOutputStream savefile = null;
        try {
            savefile = new FileOutputStream("resources/save.txt");
            myGrid.positionStates(savefile);
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

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        whichDirectionPressed(keyboardEvent);
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
        whichActionPressed(keyboardEvent);
    }
}
