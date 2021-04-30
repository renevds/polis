package polis.tiles;

import polis.actors.Actor;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.image.Image;
import polis.GameController;
import polis.views.ZoneTileView;

import java.util.*;

public abstract class ZoneTile extends MultiTile implements Observable{

    protected Image image;

    private final ZoneTileView zoneTileView;

    private InvalidationListener listener;

    protected final List<Actor> residents;

    protected double capacity;

    protected int level;

    /* deze tegel stelt een zone voor met bewoners en een level*/

    public ZoneTile(int x, int y, GameController gameController) {
        super(x, y, gameController);
        residents =  new ArrayList<>();
        updateImage();
        zoneTileView = new ZoneTileView(this);
        addListener(zoneTileView);
        eventNode = zoneTileView.getRet();
        createEvents(eventNode);
    }

    protected abstract void updateImage();

    @Override
    public void remove() {
        super.remove();
        gameController.getPC().getGameGrid().removeChildren(zoneTileView);
    }

    @Override
    public Boolean removable() {
        return true;
    }

    public Image getImage(){
        return image;
    }

    //controleer of er nog plek is voor een bewoner
    public boolean hasSpaceLeft(){
        return residents.size() + 1 <= capacity;
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

    public void addResident(Actor actor){
        residents.add(actor);
        updateImage();
    }

    public void removeResident(Actor actor){
        residents.remove(actor);
    }

    public void replaceResident(Actor oldActor, Actor newActor){
        if(residents.contains(oldActor)) {
            residents.set(residents.indexOf(oldActor), newActor);
        }
    }

    //zoek een mogelijke straat voor de Actor te plaatsen en plaats hem anders in het begin van de initiele straat
    public Street getAPossibleSpawnStreet(){
        List<Street> streets = getNeigbouringStreets();
        if(streets.size() > 0) {
            return streets.get(0);
        }
        else {
            return gameGrid.getSpawnStreet();
        }
    }

    //verwijder bewoners indien de capaciteit krimpt
    protected void kickOut(){
        while (residents.size() > capacity){
            Actor last = residents.get(residents.size() - 1);
            last.remove();
            residents.remove(last);
        }
    }

    public int getAmountOfResidents(){
        return residents.size();
    }

    @Override
    public void step() {

    }

    public double getCapacity(){
        return capacity;
    }

}
