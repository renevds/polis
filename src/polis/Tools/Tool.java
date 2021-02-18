package polis.Tools;

import javafx.scene.layout.Pane;
import polis.tiles.Tile;
import polis.gameController;

public abstract class Tool {
    gameController GC;
    Pane gamePane;

    public Tool(gameController GC){
        this.GC = GC;
        gamePane = GC.getPC().getGamePane();
    }

    abstract public void hover(Tile tile);

    abstract public void close();

    abstract public void clicked(Tile tile);
}
