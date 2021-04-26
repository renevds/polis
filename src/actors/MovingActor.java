package actors;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import polis.GameController;
import polis.tiles.Street;
import polis.tiles.Tile;

import java.util.List;
import java.util.Random;

public abstract class MovingActor extends Actor implements Observable {
    Street currentTile;
    Street lastTile;
    int steps = 0;
    protected final int MAX_AGE;
    InvalidationListener listener;

    protected MovingActor(int max_age, GameController gameController, Tile simParent, Street currentTile) {
        super(gameController, simParent);
        MAX_AGE = max_age;
        this.gameController = gameController;
        gameController.addActor(this);
        this.currentTile = currentTile;
    }

    public void step() {
        steps += 1;
        if(steps > MAX_AGE){
            gameController.removeActor(this);
            dieEffect();
        }
        else {
            boolean foundDestination = false;
            for (Tile tile: gameController.getGameGrid().getNeighbours(currentTile)){
                if(isTileDest(tile)){
                    foundDestination = true;
                    break;
                }
            }
            if(!foundDestination) {
                moveToNextStreet();
            }
        }
    }

    protected abstract boolean isTileDest(Tile tile);

    public abstract void dieEffect();

    public void moveToNextStreet() {
        List<Street> options = currentTile.getNeigbouringFreeStreets();
        Street next;
        boolean canTurn = options.contains(lastTile);
        if (lastTile != null) {
            options.remove(lastTile);
        }
        Random rd = new Random();
        if (options.size() > 0) {
            next = options.get((int) Math.round(rd.nextDouble() * (options.size() - 1)));
            next.addRoadActor(this, currentTile);
            lastTile = currentTile;
            currentTile = next;
            fireInvalidationEvent();
        }else if(canTurn){
            lastTile.addRoadActor(this, this.currentTile);
            Street temp = currentTile;
            currentTile = lastTile;
            lastTile = temp;
            fireInvalidationEvent();
        } else {
            currentTile.addRoadActorAnywhere(this);
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

    protected void fireInvalidationEvent () {
        if(listener != null) {
            listener.invalidated(this);
        }
    }

    public Street getCurrentTile(){
        return currentTile;
    }

}
