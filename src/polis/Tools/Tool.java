package polis.Tools;

import javafx.scene.layout.Pane;
import polis.tiles.Tile;
import polis.gameController;
import views.GameGrid;

public abstract class Tool {
    protected gameController GC;
    protected GameGrid gameGrid;

    public Tool(gameController GC){
        this.GC = GC;
        gameGrid = GC.getPC().getGameGrid();
    }

    public void hover(Tile tile){};

    public void close(){};

    public void clicked(Tile tile){};

    public void drag(Tile tile){};

    public void release(Tile tile){};
}
