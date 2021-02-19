package polis.Tools;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import polis.Drawers.Square;
import polis.Tools.Tool;
import polis.gameController;
import polis.tiles.StandardTile;
import polis.tiles.Tile;

import java.util.ArrayList;
import java.util.List;

public abstract class MultiPolyTool extends Tool {

    Boolean valid = false;

    public List<Polygon> hoverTiles = new ArrayList<>();

    public MultiPolyTool(gameController GC) {
        super(GC);
    }

    public Polygon createPolyOnTile(Tile tile){
        Polygon polygon = Square.drawOnTile(tile, GC);
        if(tile.getClass() == StandardTile.class){
            polygon.setFill(Color.rgb(0, 127, 255, 0.5));
        }
        else {
            polygon.setFill(Color.rgb(255, 0, 0, 0.5));
        }
        polygon.setMouseTransparent(true);
        polygon.toFront();
        return polygon;
    }

    public void hidePolys(){
        for(Polygon polygon: hoverTiles){
            GC.getPC().getGamePane().getChildren().remove(polygon);

        }
        hoverTiles.clear();
    }

    @Override
    public void close() {
        hidePolys();
    }
}
