package polis.tiles;

import polis.gameController;

import java.util.ArrayList;
import java.util.List;

public abstract class MultiTile extends Tile{

    List<MultiTileFiller> childrenTiles = new ArrayList<>();

    public MultiTile(int x, int y, gameController GC) {
        super(x, y, GC);
    }

    public void addFillerTile(MultiTileFiller multiTileFiller) {
        childrenTiles.add(multiTileFiller);
    }

    public abstract int getWidth();

    public abstract int getHeight();

    public void remove(){
        for (MultiTileFiller multiTileFiller : childrenTiles) {
            multiTileFiller.remove();
        }
        GC.getPC().getGameGrid().getChildren().remove(mainNode);
    }

}
