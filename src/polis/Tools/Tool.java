package polis.Tools;

import polis.tiles.Tile;
import polis.GameController;
import views.GameGrid;

public abstract class Tool {
    protected GameController GC;
    protected GameGrid gameGrid;

    public Tool(GameController GC){
        this.GC = GC;
        gameGrid = GC.getPC().getGameGrid();
    }

    public void hover(Tile tile){};

    public void close(){};

    public void clicked(Tile tile){};

    public void drag(Tile tile){};

    public void release(Tile tile){};

    public abstract void toFront();
}
