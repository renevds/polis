package views;

import javafx.scene.shape.Polygon;
import polis.gameController;
import polis.polisController;
import polis.tiles.Tile;

public class TilePoly extends Polygon{
    static int CELL_SIZE = polisController.getCELLSIZE();

    public TilePoly(Tile tile){
        super(
                0, 0,
                CELL_SIZE , 0.5 * CELL_SIZE,
                0, CELL_SIZE,
                -CELL_SIZE, 0.5 * CELL_SIZE
        );
        tile.getGameGrid().addChildrenToGrid(this, tile.getX(), tile.getY());
    }
}
