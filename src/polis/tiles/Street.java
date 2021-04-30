package polis.tiles;

import polis.actors.Actor;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import polis.GameController;
import polis.actors.MovingActor;
import polis.views.StreetTileView;

import java.util.ArrayList;
import java.util.List;

public class Street extends Tile implements Observable {
    private boolean removable = true;
    private final StreetTileView streetTileView;
    private InvalidationListener listener;
    private int orientationNumber;
    MovingActor[] roadActors;

    /*dit stelt een straattegel voor in de opgave heeft een straat maar 2 locaties maar in deze implementatie heeft hij
    * er 4 dit geeft een mooier effect, zeker op drukke kruispunten */

    public Street(int x, int y, GameController gameController) {
        super(x, y, gameController);
        streetTileView = new StreetTileView(this);
        addListener(streetTileView);
        eventNode = streetTileView;
        createEvents(streetTileView);
        roadActors = new MovingActor[4];
    }


    @Override
    public void remove() {
        gameGrid.removeChildren(eventNode);
    }

    @Override
    public Boolean removable() {
        return removable;
    }

    public void makeUnRemovable() {
        removable = false;
    }

    //deze functie regelt de orientatienummers die de sprite van de straten doen veranderen
    public void calculateOrientationNumber(Boolean starter) {
        orientationNumber = 0;
        if (gameGrid.validCoord(y - 1)) {
            Tile neighbourTile = gameGrid.getTileAtCoord(x, y - 1);
            if (neighbourTile.getTileType() == TileType.STREET) {
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
            if (neighbourTile.getTileType() == TileType.STREET) {
                orientationNumber += 4;
                if (starter) {
                    ((polis.tiles.Street) neighbourTile).calculateOrientationNumber(false);
                }
            }
        }

        if (gameGrid.validCoord(x - 1)) {
            Tile neighbourTile = gameGrid.getTileAtCoord(x - 1, y);
            if (neighbourTile.getTileType() == TileType.STREET) {
                orientationNumber += 8;
                if (starter) {
                    ((polis.tiles.Street) neighbourTile).calculateOrientationNumber(false);
                }
            }
        }

        if (gameGrid.validCoord(x + 1)) {
            Tile neighbourTile = gameGrid.getTileAtCoord(x + 1, y);
            if (neighbourTile.getTileType() == TileType.STREET) {
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

    public int getRoadActorPositionFromList(Actor actor){
        for (int i = 0; i < 4; i++) {
            if(roadActors[i] == actor){
                return i + 1;
            }
        }
        return -1;
    }

    private int getRoadActorPosition(Tile lasttile){
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

    public void addRoadActor(MovingActor actor, Tile lasttile) {
        int actorPosition = getRoadActorPosition(lasttile);
        roadActors[actorPosition - 1] = actor;
    }

    public void addRoadActorAnywhere(MovingActor actor){
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

    public List<Street> getNeigbouringFreeStreets(){
        List<Street> neighbours = new ArrayList<>();
        for(Tile tile: gameGrid.getNeighbours(this)){
            if(tile.getTileType() == TileType.STREET){
                neighbours.add((Street) tile);
            }
        }
        return neighbours;
    }

    @Override
    public void step(){
        roadActors = new MovingActor[4];
    }

    private boolean isFree(int actorPosition){
        return roadActors[actorPosition - 1] == null;
    }

    @Override
    public TileType getTileType() {
        return TileType.STREET;
    }
}
