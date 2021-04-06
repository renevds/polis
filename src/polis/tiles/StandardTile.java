package polis.tiles;

import polis.Drawers.Square;
import polis.gameController;

public class StandardTile extends Tile {


    public StandardTile(int x, int y, gameController GC) {
        super(x, y, GC);
    }

    @Override
    public void draw() {
        mainNode = Square.draw();
        mainNode.setStyle("-fx-fill: transparent;");
        mainNode.setTranslateX(getTileRenderX());
        mainNode.setTranslateY(getTileRenderY());
        gameGrid.getChildren().add(mainNode);
        createEvents(mainNode);
        //Text text = new Text("(" + x + ", " + y + ")");
        //text.setTranslateX(getTileRenderX());
        //text.setTranslateY(getTileRenderY() + polisController.getCELLSIZE()/2);
        //gamePane.getChildren().add(text);
    }


    public void remove() {
        gameGrid.getChildren().remove(mainNode);
    }

    @Override
    public Boolean removable() {
        return true;
    }

    @Override
    public void toFront() {

    }
}
