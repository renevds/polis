package polis.views;

import javafx.scene.paint.Color;
import polis.actors.MovingActor;
import polis.tiles.Street;
import polis.views.ActorDotView;

public class ShopperDotView extends ActorDotView{
    public ShopperDotView(MovingActor actor, Street tile) {
        super(actor, tile);
        setFill(Color.LIGHTBLUE);
    }
}
