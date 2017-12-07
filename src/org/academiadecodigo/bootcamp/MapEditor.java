package org.academiadecodigo.bootcamp;


import org.academiadecodigo.bootcamp.gridfiles.Cursor;
import org.academiadecodigo.bootcamp.gridfiles.MoveDirection;
import org.academiadecodigo.bootcamp.gridfiles.Grid;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public class MapEditor implements KeyboardHandler{

    private Grid grid;
    private Cursor myPosition;
    private FileManager filemanager;

    public MapEditor(int cols, int rows) {
        grid = new Grid(cols,rows);
        grid.createGrid();
        filemanager = new FileManager(grid);
        myPosition = new Cursor(grid);
        myPosition.createMyPosition();
        keyboardInit();
    }

    public void keyboardInit() {
        Keyboard keyboard = new Keyboard(this);
        KeyboardEvent[] keyboardEvents = new KeyboardEvent[7];
        setKeyboardEvents(keyboardEvents);
        addEventsListener(keyboard, keyboardEvents);
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
            filemanager.saveState();
        }
        if (e.getKey() == KeyboardEvent.KEY_L) {
            filemanager.loadState();
        }
    }


    private void paintCell() {
            grid.paint(myPosition.getCol(), myPosition.getRow());
            myPosition.positionColor();
    }

    private void setKeyboardEvents(KeyboardEvent[] keyboardEvents) {
        for(int i = 0; i < keyboardEvents.length; i++) {
            keyboardEvents[i] = new KeyboardEvent();
        }
        keyboardEvents[0].setKey(KeyboardEvent.KEY_UP);
        keyboardEvents[0].setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboardEvents[1].setKey(KeyboardEvent.KEY_DOWN);
        keyboardEvents[1].setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboardEvents[2].setKey(KeyboardEvent.KEY_LEFT);
        keyboardEvents[2].setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboardEvents[3].setKey(KeyboardEvent.KEY_RIGHT);
        keyboardEvents[3].setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboardEvents[4].setKey(KeyboardEvent.KEY_SPACE);
        keyboardEvents[4].setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        keyboardEvents[5].setKey(KeyboardEvent.KEY_S);
        keyboardEvents[5].setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        keyboardEvents[6].setKey(KeyboardEvent.KEY_L);
        keyboardEvents[6].setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
    }

    public void addEventsListener (Keyboard keyboard, KeyboardEvent[] keyboardEvents) {
        for(int i = 0; i < keyboardEvents.length; i++) {
            keyboard.addEventListener(keyboardEvents[i]);
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
