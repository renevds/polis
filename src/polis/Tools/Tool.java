package polis.Tools;

import polis.tiles.Tile;
import polis.GameController;
import polis.views.GameGrid;

public abstract class Tool {
    protected GameController gameController;
    protected GameGrid gameGrid;

    public Tool(GameController gameController){
        this.gameController = gameController;
        gameGrid = gameController.getPC().getGameGrid();
    }

    public void hover(Tile tile){};

    public void close(){};

    public void clicked(Tile tile){};

    public void drag(Tile tile){};

    public void release(Tile tile){};

    public abstract void toFront();
}
