package polis.actors;

import polis.GameController;
import polis.tiles.CommercialTile;
import polis.tiles.ResidentialTile;

import java.util.Properties;

public class Trader extends Actor{

    private static int MAX_AGE;
    private final CommercialTile commercialTile;

    public Trader(GameController gameController, ResidentialTile parentResidential, CommercialTile commercialTile) {
        super(gameController, parentResidential, MAX_AGE);
        this.commercialTile = commercialTile;
    }

    @Override
    protected void dieEffect() {
        commercialTile.removeTrader(this);
        replaceSelfInParentResidential(new Shopper(gameController, parentResidential, parentResidential.getAPossibleSpawnStreet()));
        remove();
    }

    @Override
    protected void notDeadStep() {
    }

    public static void setProperties(Properties engineProperties){
        MAX_AGE = Integer.parseInt(engineProperties.getProperty("trader.age"));
    }

    @Override
    public ActorType getType() {
        return ActorType.TRADER;
    }
}
