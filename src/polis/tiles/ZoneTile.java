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

    private List<InvalidationListener> listenerList = new ArrayList<> ();

    public ZoneTile(int x, int y, gameController GC) {
        super(x, y, GC);
        level = 1;
        updateImageLink();
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
