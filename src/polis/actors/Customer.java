package polis.actors;

import polis.GameController;
import polis.tiles.CommercialTile;
import polis.tiles.ResidentialTile;
import polis.tiles.ZoneTile;

import java.util.Properties;

public class Customer extends ActorWithHome{

    static private int CUSTOMER_AGE;
    CommercialTile commercialTile;

    public Customer(GameController gameController, ResidentialTile parentResidential, CommercialTile commercialTile) {
        super(gameController, parentResidential, CUSTOMER_AGE);
        this.commercialTile = commercialTile;
    }

    @Override
    protected void dieEffect() {
        commercialTile.sellGood();
        commercialTile.removeCustomer(this);
        replaceSelfInParentResidential(new Sleeper(gameController, parentResidential));
        remove();
    }

    @Override
    protected void notDeadStep() {

    }

    static public void setProperties(Properties engineProperties){
        CUSTOMER_AGE = Integer.parseInt(engineProperties.getProperty("customer.age"));
    }

    @Override
    public ActorType getType() {
        return ActorType.CUSTOMER;
    }
}
