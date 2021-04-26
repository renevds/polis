package polis.tiles;

import polis.GameController;
import views.StandardTileView;

public class StandardTile extends Tile {


    public StandardTile(int x, int y, GameController GC) {
        super(x, y, GC);
        eventNode = new StandardTileView(this);
        createEvents(eventNode);
    }

    public void remove() {}

    @Override
    public Boolean removable() {
        return true;
    }

    @Override
    public void toFront() {

    }

    @Override
    public void step() {

    }
}
