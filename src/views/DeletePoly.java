package views;

import javafx.scene.paint.Color;
import polis.Drawers.Square;

public class DeletePoly extends TilePoly{

    public DeletePoly(int x, int y, GameGrid gameGrid) {
        super(x, y, gameGrid);
        setStyle("-fx-fill: transparent; -fx-stroke: rgba(255,0,0,0.75); -fx-stroke-width: 8;");
        setFill(Color.rgb(0, 0, 0, 0));
        setMouseTransparent(true);
    }
}
