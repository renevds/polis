package polis.actors;

import polis.GameController;
import polis.tiles.*;
import polis.views.GoodDotView;

import java.util.Properties;

public class Good extends MovingActor{

    IndustrialTile industrialTile;

    private static int GOODS_AGE;

    protected Good(GameController gameController, IndustrialTile industrialTile) {
        super(gameController, null, industrialTile.getBorderingStreet(), GOODS_AGE);
        view = new GoodDotView(this, currentStreet);
        currentStreet.addRoadActorAnywhere(this);
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
                return true;
            }
        }
        return false;
    }

    @Override
    public void dieEffect() {
        industrialTile.goodsNotDelivered();
        remove();
    }

    public static void setProperties(Properties engineProperties){
        GOODS_AGE = Integer.parseInt(engineProperties.getProperty("goods.age"));
    }

}
