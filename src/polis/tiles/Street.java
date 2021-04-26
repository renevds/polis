package polis.tiles;

import actors.Actor;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import polis.GameController;
import views.StreetTileView;

import java.util.ArrayList;
import java.util.List;

public class Street extends Tile implements Observable {
    private boolean removable = true;
    private StreetTileView streetTileView;
    private InvalidationListener listener;
    private int orientationNumber;

    public Street(int x, int y, GameController GC) {
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

    public List<Street> getNeigbouringFreeStreets(){
        List<Street> neighbours = new ArrayList<>();
        for(Tile tile: gameGrid.getNeighbours(this)){
            if(tile instanceof Street){
                neighbours.add((Street) tile);
            }
        }
        return neighbours;
    }

    public int getRoadActorPositionFromList(Actor actor){
        for (int i = 0; i < 4; i++) {
            if(roadActors[i] == actor){
                return i + 1;
            }
        }
        return -1;
    }

    public int getRoadActorPosition(Tile lasttile){
        int actorposition;
        int lastX = lasttile.getX();
        int lastY = lasttile.getY();
        if(lastX < x){
            actorposition = 1;
        }
        else if(lastY < y){
            actorposition = 2;
        }
        else if(lastY > y){
            actorposition = 3;
        }
        else {
            actorposition = 4;
        }
        return actorposition;
    }

    public void addRoadActor(Actor actor, Tile lasttile) {
        int actorPosition = getRoadActorPosition(lasttile);
        roadActors[actorPosition - 1] = actor;
    }

    public void addRoadActorAnywhere(Actor actor){
        boolean found = false;
        int i = 0;
        while (!found && i < 4){
            if (isFree(i + 1)){
                roadActors[i] = actor;
                found = true;
            }
            i += 1;
        }
    }

    public void step(){
        roadActors = new Actor[4];
    }

    public boolean isFree(int actorPosition){
        return roadActors[actorPosition - 1] == null;
    }

    public void actorsToFront(){
        for (Actor actor: roadActors){
            if(actor != null) {
                actor.getView().toFront();
            }
        }
    }
}
