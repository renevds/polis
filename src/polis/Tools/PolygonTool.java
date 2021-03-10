package polis.Tools;

import javafx.scene.shape.Polygon;
import polis.gameController;
import polis.tiles.Tile;

public abstract class PolygonTool extends Tool{
    protected Polygon polygon;

    public PolygonTool(gameController GC) {
        super(GC);
    }

    public void hidePolygon(){
        if (polygon != null){
            gamePane.getChildren().remove(polygon);
        }
    }

    public void close(){
        hidePolygon();
    }
}
