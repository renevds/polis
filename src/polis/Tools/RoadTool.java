package polis.Tools;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import polis.Drawers.Square;
import polis.tiles.StandardTile;
import polis.tiles.Street;
import polis.tiles.Tile;
import polis.gameController;

public class RoadTool extends Tool{

    Polygon polygon;
    boolean valid;

    public RoadTool(gameController GC) {
        super(GC);
    }

    @Override
    public void hover(Tile tile) {
        hidePolygon();
        polygon = Square.drawOnTile(tile, GC);
        valid = (tile.getClass() == StandardTile.class);
        if(valid){
            polygon.setFill(Color.rgb(0, 127, 255, 0.5));
        }
        else {
            polygon.setFill(Color.rgb(255, 0, 0, 0.5));
        }
        polygon.setMouseTransparent(true);

    }

    @Override
    public void close() {
        hidePolygon();
    }

    public void hidePolygon(){
        if (polygon != null){
            gamePane.getChildren().remove(polygon);
        }
    }

    @Override
    public void clicked(Tile tile) {
        if(valid){
            Street newstreet = new Street(tile.getX(), tile.getY(), GC);
            GC.replaceTile(newstreet);
            newstreet.setImageString(true);
        }
    }
}
