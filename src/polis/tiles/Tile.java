package polis.tiles;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import polis.gameController;
import views.GameGrid;

abstract public class Tile {
    protected gameController GC;
    protected int x;
    protected int y;

    Node mainNode;

    protected GameGrid gameGrid;

    public Tile(int x, int y, gameController GC) {
        this.x = x;
        this.y = y;
        this.GC = GC;

        gameGrid = GC.getPC().getGameGrid();
    }

    abstract public void draw();

    public double getTileRenderX(){
        return gameGrid.getRenderX(x, y);
    }

    public double getTileRenderY(){
        return gameGrid.getRenderY(x, y);
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
        //System.out.println("hover " + this);
        GC.setCurrentHover(this);
    }

    public void clicked(){
        System.out.println("clicked " + this);
        GC.setClicked(this);
    }

    public void drag(){
        System.out.println("drag " + this);
        GC.setDrag(this);
    }

    public void release(){
        System.out.println("release " + this);
        GC.setRelease(this);
    }

    public void startDrag(Node node){
        if(GC.getPC().getGameGrid().getChildren().contains(mainNode)){
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
