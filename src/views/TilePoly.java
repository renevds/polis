package views;

import javafx.scene.shape.Polygon;
import polis.gameController;
import polis.polisController;
import polis.tiles.Tile;

public class TilePoly extends Polygon {
    static int CELL_SIZE = polisController.getCELLSIZE();
    static int size = polisController.getSize();

    public TilePoly(int x, int y, GameGrid gameGrid){
        super(
                0, 0,
                CELL_SIZE * size, 0.5 * CELL_SIZE * size,
                0, CELL_SIZE * size,
                -CELL_SIZE * size, 0.5 * CELL_SIZE * size
        );
        setTranslateX(gameGrid.getRenderX(x, y));
        setTranslateY(gameGrid.getRenderY(x, y));
        gameGrid.getChildren().add(this);
    }
}
