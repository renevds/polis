package polis.tiles;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import polis.gameController;
import polis.polisController;

abstract public class Tile {
    gameController GC;
    Pane gamePane;
    static int CELLSIZE;
    static int size;
    int x;
    int y;

    Node mainNode;


    public Tile(int x, int y, gameController GC) {
        this.x = x;
        this.y = y;
        this.GC = GC;
        size = polisController.getSize();
        CELLSIZE = polisController.getCELLSIZE();
        gamePane = GC.getPC().getGamePane();
    }

    abstract public void draw();

    public double getTileRenderX(){
        return getRenderX(x, y);
    }

    public double getTileRenderY(){
        return getRenderY(x, y);
    }

    public static double getRenderY(int x, int y){
        return (double)polisController.getCELLSIZE() * (y + x) / 2 - polisController.getCELLSIZE()/2.0;
    }

    public static double getRenderX(int x, int y){
        return -((double)CELLSIZE*gameController.getMAPSIZE())/2 + CELLSIZE * (size - y + x);
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
        System.out.println("hover " + this);
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
        if(GC.getPC().getGamePane().getChildren().contains(mainNode)){
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
}
