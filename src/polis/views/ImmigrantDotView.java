package polis.views;

import polis.actors.MovingActor;
import javafx.scene.paint.Color;
import polis.tiles.Street;

public class ImmigrantDotView extends ActorDotView{
    public ImmigrantDotView(MovingActor actor, Street tile){
        super(actor, tile);
        setFill(Color.GRAY);
    }
}
