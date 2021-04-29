package polis.actors;

import polis.GameController;
import polis.tiles.*;
import polis.simulation.ShopperDotView;

import java.util.Properties;

public class Shopper extends MovingActor{

    private static int SHOPPER_AGE;

    protected Shopper(GameController gameController, ResidentialTile residentialTile, Street currentStreet) {
        super(gameController, residentialTile, currentStreet, SHOPPER_AGE);
        view = new ShopperDotView(this, currentStreet);
    }

    @Override
    protected boolean isTileDest(Tile tile) {
        if(tile instanceof MultiTileFiller){
            tile = ((MultiTileFiller) tile).getParentZone();
        }
        if(tile instanceof CommercialTile){
            CommercialTile commercialTile =  (CommercialTile)tile;
            if(commercialTile.canTakeCustomer()) {
                Customer customer = new Customer(gameController, parentResidential, commercialTile);
                commercialTile.addCustomer(customer);
                replaceSelfInParentResidential(customer);
                parentResidential.shopFound();
                remove();
                System.out.println("shopper becomes customer");
                return true;
            }
            else{
                System.out.println("cant take customer");
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
}
