package polis.tiles;

import polis.Drawers.Square;
import polis.gameController;

public class MultiTileFiller extends Tile {
    private final MultiTile parentZone;

    public MultiTileFiller(int x, int y, gameController GC, MultiTile parentZone) {
        super(x, y, GC);
        this.parentZone = parentZone;
        parentZone.addFillerTile(this);
    }

    public void draw() {
        mainNode = Square.drawOnTile(this, GC);
        mainNode.setStyle("-fx-fill: transparent;");
        createEvents(mainNode);
    }

    @Override
    public void remove() {
        System.out.println("filler removed at (" + this.x + ", " + this.y + ")");
        GC.getPC().getGameGrid().getChildren().remove(mainNode);
        gameGrid.setTile(new StandardTile(this.x, this.y, GC));
    }

    @Override
    public void toFront() {
        if((x - parentZone.getX()) <= (parentZone.getWidth()/2.0-1) || (y - parentZone.getY()) <= (parentZone.getHeight()/2.0 -1)){
            parentZone.toFront();
            System.out.println("tofront helipad");
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
