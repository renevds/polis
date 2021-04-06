package views;

import javafx.scene.paint.Color;

public class SelectorPoly extends TilePoly{

    public SelectorPoly(int x, int y, GameGrid gameGrid) {
        super(x, y, gameGrid);
        setStyle("-fx-fill: transparent; -fx-stroke: rgba(255,255,255,0.75); -fx-stroke-width: 4;");
        setFill(Color.rgb(0,0,0,0));
        setMouseTransparent(true);
    }
}
