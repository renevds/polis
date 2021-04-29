package polis.views;

import javafx.scene.paint.Color;
import polis.tiles.Tile;

public class SelectorPoly extends TilePoly{

    public SelectorPoly(Tile tile) {
        super(tile);
        setStyle("-fx-fill: transparent; -fx-stroke: rgba(255,255,255,0.75); -fx-stroke-width: 4;");
        setFill(Color.rgb(0,0,0,0));
        setMouseTransparent(true);
    }
}
