package polis.Tools;

import javafx.scene.shape.Polygon;

import polis.GameController;
import polis.tiles.Tile;
import polis.views.ValidPoly;

import java.util.ArrayList;
import java.util.List;

public abstract class MultiPolyTool extends Tool {

    //voor tools die meerdere Polygonen tekenen

    protected Boolean valid;

    protected final List<Polygon> hoverTiles;

    protected Polygon cursorPoly;

    public MultiPolyTool(GameController gameController) {
        super(gameController);
        hoverTiles = new ArrayList<>();
        valid = false;
    }

    public Polygon createPolyOnTile(Tile tile){
        Polygon polygon = new ValidPoly(tile, tile.getTileType() == Tile.TileType.STANDARD);
        polygon.toFront();
        return polygon;
    }

    public void hidePolys(){
        for(Polygon polygon: hoverTiles){
            gameController.getPC().getGameGrid().removeChildren(polygon);
        }
        hoverTiles.clear();
    }

    private void hideCursorPoly(){
        if(cursorPoly != null) {
            gameController.getPC().getGameGrid().removeChildren(cursorPoly);
            cursorPoly = null;
        }
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
