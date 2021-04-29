package polis.tiles;

import javafx.scene.Node;
import polis.GameController;
import polis.actors.Actor;
import polis.views.GameGrid;

abstract public class Tile {
    protected GameController gameController;
    protected int x;
    protected int y;

    protected Node eventNode;

    protected GameGrid gameGrid;


    public Tile(int x, int y, GameController gameController) {
        this.x = x;
        this.y = y;
        this.gameController = gameController;
        gameGrid = gameController.getPC().getGameGrid();

    }

    public enum TileType {
        COMMERCIAL, INDUSTRIAL, STREET, RESIDENTIAL, HELICOPTER, STANDARD, FILLER;
    }

    public abstract TileType getTileType();

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public abstract void remove();

    public abstract Boolean removable();

    private void hover(){
        gameController.setCurrentHover(this);
    }

    private void clicked(){
        gameController.setClicked(this);
    }

    private void drag(){
        gameController.setDrag(this);
    }

    private void release(){
        gameController.setRelease(this);
    }

    private void startDrag(Node node){
        if(gameController.getPC().getGameGrid().getChildren().contains(eventNode)){
            node.startFullDrag();
        }
    }

    public void createEvents(Node node){
        node.setOnMouseEntered(mouseEvent  -> hover());
        node.setOnMousePressed(mouseEvent  -> clicked());
        node.setOnDragDetected(mouseEvent -> startDrag(node));
        node.setOnMouseDragOver(mouseEvent -> drag());
        node.setOnMouseReleased(mouseEvent -> release());
    }

    public abstract  void toFront();

    public GameGrid getGameGrid(){
        return gameGrid;
    }

    public abstract void step();


    public boolean acceptsResident(Actor actor){
        return false;
    };

    public Tile getParentTile(){
        return this;
    }

}
