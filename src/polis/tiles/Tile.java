package polis.tiles;

import polis.actors.Actor;
import javafx.scene.Node;
import polis.GameController;
import views.GameGrid;

abstract public class Tile {
    protected GameController GC;
    protected int x;
    protected int y;

    protected Node eventNode;

    protected GameGrid gameGrid;

    Actor[] roadActors;

    public Tile(int x, int y, GameController GC) {
        this.x = x;
        this.y = y;
        this.GC = GC;
        gameGrid = GC.getPC().getGameGrid();
        roadActors = new Actor[4];
    }


    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public abstract void remove();

    public abstract Boolean removable();

    private void hover(){
        GC.setCurrentHover(this);
    }

    private void clicked(){
        GC.setClicked(this);
    }

    private void drag(){
        GC.setDrag(this);
    }

    private void release(){
        GC.setRelease(this);
    }

    private void startDrag(Node node){
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

    public abstract void step();

}
