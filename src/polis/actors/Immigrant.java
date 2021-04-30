package polis.actors;

import polis.GameController;
import polis.tiles.Street;
import polis.tiles.Tile;
import polis.views.ImmigrantDotView;

import java.util.Properties;

public class Immigrant extends polis.actors.MovingActor {
    private static int IMMIGRANT_AGE;

    public Immigrant(GameController gameController, Street tile){
        super(gameController, null, tile, IMMIGRANT_AGE);
        view = new ImmigrantDotView(this, tile);
    }

    @Override
    public boolean isTileDest(Tile tile){
        tile = tile.getParentTile();
        if(tile.acceptsResident(this)) {
            remove();
            return true;
        }
        return false;
    }

    @Override
    public void dieEffect() {
        gameController.getRegion().slowDown();
        remove();
    }

    public static void setProperties(Properties engineProperties){
        IMMIGRANT_AGE = Integer.parseInt(engineProperties.getProperty("immigrant.age"));
    }

    @Override
    public ActorType getType() {
        return ActorType.IMMIGRANT;
    }
}
