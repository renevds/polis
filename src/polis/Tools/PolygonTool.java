package polis.Tools;

import javafx.scene.shape.Polygon;
import polis.GameController;

public abstract class PolygonTool extends Tool{
    protected Polygon polygon;

    public PolygonTool(GameController gameController) {
        super(gameController);
    }

    public void hidePolygon(){
        if (polygon != null){
            gameGrid.removeChildren(polygon);
        }
    }

    @Override
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
