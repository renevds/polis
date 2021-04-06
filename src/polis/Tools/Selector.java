package polis.Tools;

import javafx.scene.paint.Color;
import polis.Drawers.Square;
import polis.tiles.Tile;
import polis.gameController;
import polis.tiles.ZoneFiller;
import polis.tiles.ZoneTile;
import views.SelectorPoly;
import views.TilePoly;

public class Selector extends PolygonTool {
    
    public Selector(gameController GC){
        super(GC);
    }

    public void hover(Tile tile) {
        hidePolygon();
        polygon = new SelectorPoly(tile.getX(), tile.getY(), gameGrid);
        //System.out.print("(" + tile.getX() + ", " + tile.getY() + ") " + tile);
        //System.out.println(" hovered.");
    }

    @Override
    public void clicked(Tile tile){
        if(tile instanceof ZoneTile){
            ((ZoneTile)tile).increaseLevel();
        }
        else if(tile instanceof ZoneFiller){
            ((ZoneFiller)tile).getParentZone().increaseLevel();
        }
    }

}
