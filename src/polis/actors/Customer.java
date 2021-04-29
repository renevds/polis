package polis.actors;

import polis.GameController;
import polis.tiles.CommercialTile;
import polis.tiles.ResidentialTile;

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
        System.out.println("customer to sleeper");
    }

    @Override
    protected void notDeadStep() {

    }

    static public void setProperties(Properties engineProperties){
        CUSTOMER_AGE = Integer.parseInt(engineProperties.getProperty("customer.age"));
    }
}
