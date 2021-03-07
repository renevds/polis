package polis.Tools;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import polis.Drawers.Square;

import polis.gameController;
import polis.tiles.StandardTile;
import polis.tiles.Tile;
import polis.tiles.ZoneFiller;

import java.util.ArrayList;
import java.util.List;

public abstract class MultiPolyTool extends Tool {

    Boolean valid = false;

    List<Polygon> hoverTiles = new ArrayList<>();

    Polygon cursorPoly;

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

    public void hideCursorPoly(){
        GC.getPC().getGamePane().getChildren().remove(cursorPoly);
        cursorPoly = null;
    }

    @Override
    public void close() {
        System.out.println("close");
        hidePolys();
        hideCursorPoly();
    }


}
