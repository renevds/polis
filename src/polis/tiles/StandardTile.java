package polis.tiles;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import polis.Drawers.Square;
import polis.gameController;
import polis.polisController;

public class StandardTile extends Tile{


    public StandardTile(int x, int y, gameController GC) {
        super(x, y, GC);
    }

    @Override
    public void draw(){
        mainNode = Square.draw();
        mainNode.setStyle("-fx-fill: transparent;");
        mainNode.setTranslateX(getTileRenderX());
        mainNode.setTranslateY(getTileRenderY());
        gamePane.getChildren().add(mainNode);
        createEvents(mainNode);
        //Text text = new Text("(" + x + ", " + y + ")");
        //text.setTranslateX(getTileRenderX());
        //text.setTranslateY(getTileRenderY() + polisController.getCELLSIZE()/2);
        //gamePane.getChildren().add(text);
    }


    public void remove(){
        gamePane.getChildren().remove(mainNode);
    }

    @Override
    public Boolean removable() {
        return true;
    }
}
