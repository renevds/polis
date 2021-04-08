package polis.tiles;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import polis.gameController;
import views.StreetTileView;

public class Street extends Tile implements Observable {
    private boolean removable = true;
    private StreetTileView streetTileView;
    private InvalidationListener listener;
    private int orientationNumber;

    public Street(int x, int y, gameController GC) {
        super(x, y, GC);
        streetTileView = new StreetTileView(this);
        addListener(streetTileView);
        eventNode = streetTileView;
        createEvents(streetTileView);
    }


    public void remove() {
        gameGrid.getChildren().remove(streetTileView);
    }

    @Override
    public Boolean removable() {
        return removable;
    }

    @Override
    public void toFront() {
        streetTileView.toFront();
    }

    public void makeUnRemovable() {
        removable = false;
    }

    public void calculateOrientationNumber(Boolean starter) {
        orientationNumber = 0;
        if (gameGrid.validCoord(y - 1)) {
            Tile neighbourTile = gameGrid.getTileAtCoord(x, y - 1);
            if (neighbourTile instanceof polis.tiles.Street) {
                orientationNumber += 1;
                if (starter) {
                    ((polis.tiles.Street) neighbourTile).calculateOrientationNumber(false);
                }
            }
        }
        if (!removable) {
            orientationNumber = 1;
        }

        if (gameGrid.validCoord(y + 1)) {
            Tile neighbourTile = gameGrid.getTileAtCoord(x, y + 1);
            if (neighbourTile instanceof polis.tiles.Street) {
                orientationNumber += 4;
                if (starter) {
                    ((polis.tiles.Street) neighbourTile).calculateOrientationNumber(false);
                }
            }
        }

        if (gameGrid.validCoord(x - 1)) {
            Tile neighbourTile = gameGrid.getTileAtCoord(x - 1, y);
            if (neighbourTile instanceof polis.tiles.Street) {
                orientationNumber += 8;
                if (starter) {
                    ((polis.tiles.Street) neighbourTile).calculateOrientationNumber(false);
                }
            }
        }

        if (gameGrid.validCoord(x + 1)) {
            Tile neighbourTile = gameGrid.getTileAtCoord(x + 1, y);
            if (neighbourTile instanceof polis.tiles.Street) {
                orientationNumber += 2;
                if (starter) {
                    ((polis.tiles.Street) neighbourTile).calculateOrientationNumber(false);
                }
            }
        }
        fireInvalidationEvent();
    }

    @Override
    public void addListener(InvalidationListener invalidationListener) {
        listener = invalidationListener;
    }

    @Override
    public void removeListener(InvalidationListener invalidationListener) {
        listener = null;
    }

    private void fireInvalidationEvent() {
        if (listener != null) {
            listener.invalidated(this);
        }
    }

    public int getOrientationNumber() {
        return orientationNumber;
    }
}
