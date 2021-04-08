package polis.tiles;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.image.Image;
import polis.gameController;
import views.ZoneTileView;

import java.util.ArrayList;
import java.util.List;

public abstract class ZoneTile extends MultiTile implements Observable{
    protected static int MAX_LEVEL = 4;
    protected int level;

    protected Image image;

    protected ZoneTileView zoneTileView;

    private InvalidationListener listener;

    public ZoneTile(int x, int y, gameController GC) {
        super(x, y, GC);
        level = 1;
        updateImageLink();
        zoneTileView = new ZoneTileView(this);
        addListener(zoneTileView);
        eventNode = zoneTileView.getRet();
        createEvents(eventNode);
    }


    public void increaseLevel() {
        level = level % MAX_LEVEL + 1;
        updateImageLink();
        fireInvalidationEvent();
    }

    public abstract void updateImageLink();

    public void remove() {
        super.remove();
        GC.getPC().getGameGrid().getChildren().remove(zoneTileView);
    }

    @Override
    public Boolean removable() {
        return true;
    }

    public void toFront() {
        zoneTileView.toFront();
    }

    public Image getImage(){
        return image;
    };

    @Override
    public void addListener(InvalidationListener invalidationListener) {
        listener = invalidationListener;
    }

    @Override
    public void removeListener(InvalidationListener invalidationListener) {
        listener = null;
    }

    private void fireInvalidationEvent () {
        if(listener != null) {
            listener.invalidated(this);
        }
    }

}
