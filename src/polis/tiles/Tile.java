package polis.tiles;

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
        GC.setCurrentHover(this);
    }

    public void clicked(){
        System.out.println("clicked" + this);
        GC.setClicked(this);
    }

}
