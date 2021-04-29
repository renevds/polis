package polis.Tools;

import javafx.scene.shape.Polygon;

import polis.GameController;
import polis.tiles.StandardTile;
import polis.tiles.Tile;
import polis.views.ValidPoly;

import java.util.ArrayList;
import java.util.List;

public abstract class MultiPolyTool extends Tool {

    protected Boolean valid = false;

    protected List<Polygon> hoverTiles = new ArrayList<>();

    protected Polygon cursorPoly;

    public MultiPolyTool(GameController GC) {
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

    private void hideCursorPoly(){
        GC.getPC().getGameGrid().getChildren().remove(cursorPoly);
        cursorPoly = null;
    }

    @Override
    public void close() {
        hidePolys();
        hideCursorPoly();
    }

    @Override
    public void toFront() {
        for (Polygon polygon: hoverTiles){
            polygon.toFront();
        }
        if(cursorPoly != null) {
            cursorPoly.toFront();
        }
    }
}
