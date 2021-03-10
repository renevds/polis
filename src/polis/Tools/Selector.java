package polis.Tools;

import javafx.scene.paint.Color;
import polis.Drawers.Square;
import polis.tiles.Tile;
import polis.gameController;
import polis.tiles.ZoneFiller;
import polis.tiles.ZoneTile;

public class Selector extends PolygonTool {
    
    public Selector(gameController GC){
        super(GC);
    }

    public void hover(Tile tile) {
        hidePolygon();
        polygon = Square.drawOnTile(tile, GC);
        polygon.setStyle("-fx-fill: transparent; -fx-stroke: rgba(255,255,255,0.75); -fx-stroke-width: 4;");
        polygon.setFill(Color.rgb(0,0,0,0));
        polygon.setMouseTransparent(true);
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
