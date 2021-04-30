package polis.tiles;

import polis.GameController;
import polis.views.StandardTileView;

public class StandardTile extends Tile {

    //standaard lege tegel

    public StandardTile(int x, int y, GameController gameController) {
        super(x, y, gameController);
        eventNode = new StandardTileView(this);
        createEvents(eventNode);
    }

    @Override
    public void remove() {
        gameGrid.removeChildren(eventNode);
    }

    @Override
    public Boolean removable() {
        return true;
    }

    @Override
    public void step() {

    }

    @Override
    public TileType getTileType() {
        return TileType.STANDARD;
    }
}
