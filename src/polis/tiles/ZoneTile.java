package polis.tiles;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import polis.gameController;
import views.ZoneTileView;

import java.util.ArrayList;
import java.util.List;

public abstract class ZoneTile extends Tile implements Observable{
    protected static int MAX_LEVEL = 4;
    protected int level;

    protected String imageLink;

    protected ZoneTileView zoneTileView;

    List<MultiTileFiller> childrenTiles = new ArrayList<>();

    private List<InvalidationListener> listenerList = new ArrayList<> ();

    public ZoneTile(int x, int y, gameController GC) {
        super(x, y, GC);
        level = 1;
        updateImageLink();
    }

    public void addFillerTile(MultiTileFiller multiTileFiller) {
        childrenTiles.add(multiTileFiller);
    }

    public void increaseLevel() {
        level = level % MAX_LEVEL + 1;
        updateImageLink();
        fireInvalidationEvent();
    }

    public void draw() {
        zoneTileView = new ZoneTileView(this);
        addListener(zoneTileView);
        mainNode = zoneTileView.getRet();
        createEvents(mainNode);
    }

    public abstract void updateImageLink();

    public void remove() {
        for (MultiTileFiller multiTileFiller : childrenTiles) {
            multiTileFiller.remove();
        }
        System.out.println("zone removed");
        GC.getPC().getGameGrid().getChildren().remove(mainNode);
        GC.getPC().getGameGrid().getChildren().remove(zoneTileView);
        System.out.println(GC);
    }

    @Override
    public Boolean removable() {
        return true;
    }

    public void toFront() {
        zoneTileView.toFront();
    }

    public String getImageLink(){
        return imageLink;
    };

    @Override
    public void addListener(InvalidationListener invalidationListener) {
        listenerList.add(invalidationListener);
    }

    @Override
    public void removeListener(InvalidationListener invalidationListener) {
        listenerList.remove(invalidationListener);
    }

    private void fireInvalidationEvent () {
        for (InvalidationListener listener : listenerList) {
            listener.invalidated(this);
        }
    }

}
