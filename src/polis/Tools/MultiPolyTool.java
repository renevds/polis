package polis.Tools;

import javafx.scene.shape.Polygon;

import polis.gameController;
import polis.tiles.StandardTile;
import polis.tiles.Tile;
import views.ValidPoly;

import java.util.ArrayList;
import java.util.List;

public abstract class MultiPolyTool extends Tool {

    protected Boolean valid = false;

    protected List<Polygon> hoverTiles = new ArrayList<>();

    protected Polygon cursorPoly;

    public MultiPolyTool(gameController GC) {
        super(GC);
    }

    public Polygon createPolyOnTile(Tile tile){
        Polygon polygon = new ValidPoly(tile, tile.getClass() == StandardTile.class);
        polygon.toFront();
        return polygon;
    }

    public void hidePolys(){
        for(Polygon polygon: hoverTiles){
            GC.getPC().getGameGrid().getChildren().remove(polygon);
        }
        hoverTiles.clear();
    }

    public void hideCursorPoly(){
        GC.getPC().getGameGrid().getChildren().remove(cursorPoly);
        cursorPoly = null;
    }

    @Override
    public void close() {
        hidePolys();
        hideCursorPoly();
    }


}
