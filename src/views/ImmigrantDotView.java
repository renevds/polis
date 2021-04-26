package views;

import actors.Actor;
import actors.MovingActor;
import javafx.scene.paint.Color;
import polis.tiles.Street;
import polis.tiles.Tile;

public class ImmigrantDotView extends ActorDotView{
    public ImmigrantDotView(MovingActor actor, Street tile){
        super(actor, tile);
        setFill(Color.GRAY);
    }
}
