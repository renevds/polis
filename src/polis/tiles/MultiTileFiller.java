package polis.tiles;

import polis.GameController;
import polis.views.StandardTileView;

public class MultiTileFiller extends Tile {
    private final MultiTile parentZone;

    public MultiTileFiller(int x, int y, GameController gameController, MultiTile parentZone) {
        super(x, y, gameController);
        this.parentZone = parentZone;
        parentZone.addFillerTile(this);
        eventNode = new StandardTileView(this);
        createEvents(eventNode);
    }

    @Override
    public void remove() {
        gameGrid.removeChildren(eventNode);
        gameGrid.setTile(new StandardTile(this.x, this.y, gameController));
    }

    @Override
    public void step() {

    }

    @Override
    public Boolean removable() {
        return parentZone.removable();
    }

    @Override
    public TileType getTileType() {
        return TileType.FILLER;
    }

    @Override
    public Tile getParentTile() {
        return parentZone;
    }

}
