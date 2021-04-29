package polis.actors;

import polis.GameController;
import polis.tiles.*;
import views.GoodDotView;

import java.util.Properties;

public class Good extends MovingActor{

    IndustrialTile industrialTile;

    private static int MAX_AGE;

    protected Good(GameController gameController, IndustrialTile industrialTile) {
        super(MAX_AGE, gameController, null, industrialTile.getBorderingStreet());
        System.out.println(currentStreet);
        this.view = new GoodDotView(this, currentStreet);
        this.industrialTile = industrialTile;
    }

    @Override
    protected boolean isTileDest(Tile tile) {
        if(tile instanceof MultiTileFiller){
            tile = ((MultiTileFiller) tile).getParentZone();
        }
        if(tile instanceof CommercialTile){
            CommercialTile commercialTile =  (CommercialTile)tile;
            if(commercialTile.hasSpaceLeftForGood()) {
                commercialTile.addGood();
                industrialTile.goodsDelivered();
                remove();
            }
        }
        return false;
    }

    @Override
    public void dieEffect() {
        industrialTile.goodsNotDelivered();
    }

    public void setProperties(Properties engineProperties){
        MAX_AGE = Integer.parseInt(engineProperties.getProperty("goods.age"));
    }

}
