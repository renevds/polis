package polis.actors;

import polis.GameController;
import polis.tiles.*;
import polis.views.ShopperDotView;

import java.util.Properties;

public class Shopper extends MovingActor{

    private static int SHOPPER_AGE;

    protected Shopper(GameController gameController, ResidentialTile parentResidential, Street currentStreet) {
        super(gameController, parentResidential, currentStreet, SHOPPER_AGE);
        view = new ShopperDotView(this, currentStreet);
    }

    @Override
    protected boolean isTileDest(Tile tile) {
        if(tile.getTileType() == Tile.TileType.COMMERCIAL){
            if(tile.acceptsResident(this)) {
                parentResidential.shopFound();
                remove();
                return true;
            }
        }
        return false;
    }

    @Override
    public void dieEffect() {
        parentResidential.shopNotFound();
        replaceSelfInParentResidential(new Sleeper(gameController, parentResidential));
    }

    public static void setProperties(Properties properties){
        SHOPPER_AGE = Integer.parseInt(properties.getProperty("shopper.age"));
    }

    @Override
    public ActorType getType() {
        return ActorType.SHOPPER;
    }
}
