package views;

import javafx.scene.paint.Color;
import polis.tiles.Tile;

public class ValidPoly extends TilePoly{
    public ValidPoly(Tile tile, Boolean valid) {
        super(tile);
        setFill(valid ? Color.rgb(0, 127, 255, 0.5): Color.rgb(255, 0, 0, 0.5));
        setMouseTransparent(true);
        toFront();
    }
}
