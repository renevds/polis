package views;

import javafx.scene.paint.Color;
import polis.actors.MovingActor;
import polis.tiles.Street;

public class GoodDotView extends ActorDotView{
    public GoodDotView(MovingActor actor, Street tile) {
        super(actor, tile);
        setFill(Color.YELLOW);
    }
}
