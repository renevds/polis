package views;

import javafx.scene.paint.Color;
import polis.tiles.StandardTile;

public class ValidPoly extends TilePoly{
    public ValidPoly(int x, int y, GameGrid gameGrid, Boolean valid) {
        super(x, y, gameGrid);
        setFill(valid ? Color.rgb(0, 127, 255, 0.5): Color.rgb(255, 0, 0, 0.5));
        setMouseTransparent(true);
        toFront();
    }
}
