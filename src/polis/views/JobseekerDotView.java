package polis.views;

import polis.actors.MovingActor;
import javafx.scene.paint.Color;
import polis.tiles.Street;

public class JobseekerDotView extends ActorDotView {
    public JobseekerDotView(MovingActor actor, Street tile) {
        super(actor, tile);
        setFill(Color.PERU);
    }
}
