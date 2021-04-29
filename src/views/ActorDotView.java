package views;

import polis.actors.Actor;
import polis.actors.MovingActor;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.shape.Circle;
import polis.tiles.Street;

public class ActorDotView extends Circle implements InvalidationListener {
    private MovingActor actor;

    public ActorDotView(MovingActor actor, Street tile){
        super();
        setCenterX(0);
        setCenterY(0);
        setRadius(12);
        setMouseTransparent(true);

        this.actor = actor;
        actor.addListener(this);
        actor.getGameController().getGameGrid().addActor(this, tile);
    }

    public Actor getActor() {
        return actor;
    }

    @Override
    public void invalidated(Observable observable) {
        actor.getGameController().getGameGrid().removeChildren(this);
        actor.getGameController().getGameGrid().addActor(this, actor.getCurrentStreet());
    }
}
