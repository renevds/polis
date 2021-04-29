package polis.tiles;

import polis.GameController;
import polis.views.HelicopterTileView;

public class HelicopterTile extends MultiTile{

    private HelicopterTileView helicopterTileView;

    public HelicopterTile(int x, int y, GameController GC) {
        super(x, y, GC);
        helicopterTileView = new HelicopterTileView(this);
        eventNode = helicopterTileView.getRet();
        createEvents(eventNode);
        width = 1;
        height = 3;
    }

    @Override
    public void remove() {
        super.remove();
        gameGrid.removeChildren(helicopterTileView);
    }

    @Override
    public Boolean removable() {
        return true;
    }

    @Override
    public void toFront() {
        helicopterTileView.toFront();
    }
}
