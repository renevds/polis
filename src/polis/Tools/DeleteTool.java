package polis.Tools;

import javafx.scene.paint.Color;
import polis.Drawers.Square;
import polis.gameController;
import polis.tiles.StandardTile;
import polis.tiles.Street;
import polis.tiles.Tile;

public class DeleteTool extends PolygonTool{

    public DeleteTool(gameController GC) {
        super(GC);
    }

    @Override
    public void hover(Tile tile) {
        hidePolygon();
        polygon = Square.drawOnTile(tile, GC);
        polygon.setStyle("-fx-fill: transparent; -fx-stroke: rgba(255,0,0,0.75); -fx-stroke-width: 8;");
        polygon.setFill(Color.rgb(0,0,0,0));
        polygon.setMouseTransparent(true);
    }

    @Override
    public void clicked(Tile tile) {
        if(tile.removable()){
            GC.replaceTile(new StandardTile(tile.getX(), tile.getY(), GC));
            if(tile instanceof Street){
                ((Street) tile).makeNeighboursRecalulate();
            }
            polygon.toFront();
        }

    }

}
