package polis.tiles;

import actors.Actor;
import javafx.scene.Node;
import polis.GameController;
import views.GameGrid;

import java.util.ArrayList;
import java.util.List;

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

    public abstract void step();

}
