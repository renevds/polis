package views;

import actors.Actor;
import actors.MovingActor;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.shape.Circle;
import polis.tiles.Street;
import polis.tiles.Tile;

public class ActorDotView extends Circle implements InvalidationListener {
    MovingActor actor;

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
        actor.getGameController().getGameGrid().addActor(this, actor.getCurrentTile());
    }
}
