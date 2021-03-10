package polis.Tools;

import javafx.scene.layout.Pane;
import polis.tiles.Tile;
import polis.gameController;

public abstract class Tool {
    protected gameController GC;
    protected Pane gamePane;

    public Tool(gameController GC){
        this.GC = GC;
        gamePane = GC.getPC().getGamePane();
    }

    public void hover(Tile tile){};

    public void close(){};

    public void clicked(Tile tile){};

    public void drag(Tile tile){};

    public void release(Tile tile){};
}
