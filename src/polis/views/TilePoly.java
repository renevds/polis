package polis.views;

import javafx.scene.shape.Polygon;
import polis.PolisController;
import polis.tiles.Tile;

public class TilePoly extends Polygon{
    private static int CELL_SIZE = PolisController.getCELLSIZE();

    public TilePoly(Tile tile){
        this(tile.getGameGrid(), tile.getX(), tile.getY());
    }

    public TilePoly(GameGrid gameGrid, int x, int y){
        super(
                0, 0,
                CELL_SIZE , 0.5 * CELL_SIZE,
                0, CELL_SIZE,
                -CELL_SIZE, 0.5 * CELL_SIZE
        );
        gameGrid.addChildrenToGrid(this, x, y);
    }
}
