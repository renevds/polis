package polis.tiles;

import polis.gameController;
import views.StandardTileView;

public class MultiTileFiller extends Tile {
    private final MultiTile parentZone;

    public MultiTileFiller(int x, int y, gameController GC, MultiTile parentZone) {
        super(x, y, GC);
        this.parentZone = parentZone;
        parentZone.addFillerTile(this);
        eventNode = new StandardTileView(this);
        createEvents(eventNode);
    }

    @Override
    public void remove() {
        gameGrid.removeChildren(eventNode);
        gameGrid.setTile(new StandardTile(this.x, this.y, GC));
    }

    @Override
    public void toFront() {
        if((x - parentZone.getX()) <= (parentZone.getWidth()/2.0-1) || (y - parentZone.getY()) <= (parentZone.getHeight()/2.0 -1)){
            parentZone.toFront();
        }
    }

    @Override
    public Boolean removable() {
        return parentZone.removable();
    }

    public MultiTile getParentZone() {
        return parentZone;
    }
}
