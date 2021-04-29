package polis.tiles;

import polis.actors.Actor;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.image.Image;
import polis.GameController;
import polis.actors.Immigrant;
import polis.views.ZoneTileView;

import java.util.*;

public abstract class ZoneTile extends MultiTile implements Observable{
    private static int MAX_LEVEL = 4;

    protected Image image;

    private ZoneTileView zoneTileView;

    private InvalidationListener listener;

    protected List<Actor> residents = new ArrayList<>();

    protected double capacity;

    protected int level;

    public ZoneTile(int x, int y, GameController gameController) {
        super(x, y, gameController);
        updateImage();
        zoneTileView = new ZoneTileView(this);
        addListener(zoneTileView);
        eventNode = zoneTileView.getRet();
        createEvents(eventNode);
    }

    protected abstract void updateImage();

    public void remove() {
        super.remove();
        gameController.getPC().getGameGrid().getChildren().remove(zoneTileView);
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


    public Street getBorderingStreet(){
        List<Street> streets = getNeigbouringFreeStreets();
        if(streets.size() > 0) {
            return streets.get(0);
        }
        else {
            return gameGrid.getSpawnStreet();
        }
    }

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
