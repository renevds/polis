package polis.Tools;

import javafx.scene.shape.Polygon;
import polis.GameController;

public abstract class PolygonTool extends Tool{
    protected Polygon polygon;

    public PolygonTool(GameController GC) {
        super(GC);
    }

    public void hidePolygon(){
        if (polygon != null){
            gameGrid.getChildren().remove(polygon);
        }
    }

    public void close(){
        hidePolygon();
    }

    @Override
    public void toFront() {
        if(polygon != null) {
            polygon.toFront();
        }
    }
}
