package polis.actors;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.Node;
import polis.GameController;
import polis.tiles.ResidentialTile;
import polis.tiles.Street;
import polis.tiles.Tile;

import java.util.List;
import java.util.Random;

public abstract class MovingActor extends Actor implements Observable {
    Street currentStreet;
    private Street lastTile;
    private InvalidationListener listener;

    /*stelt een Actor die zich over de straten verplaatst voor
     is een Observable voor de standard ActorDotView*/

    protected MovingActor(GameController gameController, ResidentialTile residentialTile, Street currentStreet, int maxAge) {
        super(gameController, residentialTile, maxAge);
        this.currentStreet = currentStreet;
    }

    protected MovingActor(int maxAge, GameController gameController, ResidentialTile residentialTile) {
        this(gameController, residentialTile, residentialTile.getAPossibleSpawnStreet(), maxAge);
    }

    @Override
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

    //kijk voor een bepaalde tegel of het een geldige bestemming en doe de nodige actie
    protected abstract boolean isTileDest(Tile tile);

    //beweeg naar de volgende straat
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


    @Override
    public Node getView(){
        return view;
    }

}
