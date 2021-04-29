package polis.actors;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.Node;
import polis.GameController;
import polis.tiles.ResidentialTile;
import polis.tiles.Street;
import polis.tiles.Tile;
import polis.tiles.ZoneTile;

import java.util.List;
import java.util.Random;

public abstract class MovingActor extends ActorWithHome implements Observable {
    Street currentStreet;
    private Street lastTile;
    private InvalidationListener listener;

    protected MovingActor(GameController gameController, ResidentialTile residentialTile, Street currentStreet, int maxAge) {
        super(gameController, residentialTile, maxAge);
        this.currentStreet = currentStreet;
    }

    protected MovingActor(int maxAge, GameController gameController, ResidentialTile residentialTile) {
        this(gameController, residentialTile, residentialTile.getBorderingStreet(), maxAge);
    }

    public void notDeadStep() {
        boolean foundDestination = false;
        for (Tile tile: gameController.getGameGrid().getNeighbours(currentStreet)){
            if(isTileDest(tile)){
                foundDestination = true;
                break;
            }
        }
        if(!foundDestination) {
            moveToNextStreet();
        }
    }

    protected abstract boolean isTileDest(Tile tile);

    public abstract void dieEffect();

    private void moveToNextStreet() {
        List<Street> options = currentStreet.getNeigbouringFreeStreets();
        Street next;
        boolean canTurn = options.contains(lastTile);
        if (lastTile != null) {
            options.remove(lastTile);
        }
        Random rd = new Random();
        if (options.size() > 0) {
            next = options.get((int) Math.round(rd.nextDouble() * (options.size() - 1)));
            next.addRoadActor(this, currentStreet);
            lastTile = currentStreet;
            currentStreet = next;
            fireInvalidationEvent();
        }else if(canTurn){
            lastTile.addRoadActor(this, this.currentStreet);
            Street temp = currentStreet;
            currentStreet = lastTile;
            lastTile = temp;
            fireInvalidationEvent();
        } else {
            currentStreet.addRoadActorAnywhere(this);
        }

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
        if(listener != null) {
            listener.invalidated(this);
        }
    }

    public Street getCurrentStreet(){
        return currentStreet;
    }


    public Node getView(){
        return view;
    }

}
