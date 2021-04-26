package polis.tiles;

import polis.GameController;

import java.util.ArrayList;
import java.util.List;

public abstract class MultiTile extends Tile{
    protected int width;
    protected int height;

    private List<MultiTileFiller> childrenTiles = new ArrayList<>();

    public MultiTile(int x, int y, GameController GC) {
        super(x, y, GC);
    }

    public void addFillerTile(MultiTileFiller multiTileFiller) {
        childrenTiles.add(multiTileFiller);
    }

    public int getWidth(){
        return width;
    };

    public int getHeight(){
        return height;
    };

    public void remove(){
        for (MultiTileFiller multiTileFiller : childrenTiles) {
            multiTileFiller.remove();
        }
        GC.getPC().getGameGrid().getChildren().remove(eventNode);
    }

    @Override
    public void step() {

    }
}
