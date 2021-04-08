package polis.tiles;

import javafx.scene.Node;
import polis.gameController;
import views.GameGrid;

abstract public class Tile {
    protected gameController GC;
    protected int x;
    protected int y;

    protected Node eventNode;

    protected GameGrid gameGrid;

    public Tile(int x, int y, gameController GC) {
        this.x = x;
        this.y = y;
        this.GC = GC;

        gameGrid = GC.getPC().getGameGrid();
    }


    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public abstract void remove();

    public abstract Boolean removable();

    public void hover(){
        GC.setCurrentHover(this);
    }

    public void clicked(){
        GC.setClicked(this);
    }

    public void drag(){
        GC.setDrag(this);
    }

    public void release(){
        GC.setRelease(this);
    }

    public void startDrag(Node node){
        if(GC.getPC().getGameGrid().getChildren().contains(eventNode)){
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

}
