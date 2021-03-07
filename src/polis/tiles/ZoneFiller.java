package polis.tiles;

import polis.Drawers.Square;
import polis.gameController;

public class ZoneFiller extends Tile{
    ZoneTile parentZone;

    public ZoneFiller(int x, int y, gameController GC, ZoneTile parentZone) {
        super(x, y, GC);
        this.parentZone = parentZone;
        parentZone.addFillerTile(this);
    }

    public void draw(){
        mainNode = Square.drawOnTile(this, GC);
        mainNode.setStyle("-fx-fill: transparent;");
        createEvents(mainNode);
    }

    @Override
    public void remove() {
        parentZone.remove();
    }

    public void removeSelf(){
        GC.getPC().getGamePane().getChildren().remove(mainNode);
        GC.setTile(new StandardTile(this.getX(), this.getY(), GC));
    }

    @Override
    public Boolean removable() {
        return parentZone.removable();
    }

    public ZoneTile getParentZone() {
        return parentZone;
    }
}
