package polis.Tiles;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import polis.gameController;
import polis.polisController;

public abstract class Tile {
    gameController GC;
    Pane gamePane;
    int CELLSIZE;
    int size;
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

    public double getRenderX(){
        return -((double)CELLSIZE*gameController.getMAPSIZE())/2 + CELLSIZE * (size - y + x);
    }

    public double getRenderY(){
        return (double)polisController.getCELLSIZE() * (y + x) / 2;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public abstract void remove();

    public abstract Boolean removable();

}
