package polis.actors;

import polis.GameController;
import polis.tiles.MultiTileFiller;
import polis.tiles.ResidentialTile;
import polis.tiles.Street;
import polis.tiles.Tile;
import views.ImmigrantDotView;

import java.util.Properties;

public class Immigrant extends polis.actors.MovingActor {
    private static int IMMIGRANT_AGE;

    public Immigrant(GameController gameController, Street tile){
        super(IMMIGRANT_AGE, gameController, null, tile);
        this.view = new ImmigrantDotView(this, tile);
    }

    public boolean isTileDest(Tile tile){
        if(tile instanceof MultiTileFiller){
            tile = ((MultiTileFiller) tile).getParentZone();
        }
        if(tile instanceof ResidentialTile){
            ResidentialTile residentialTile =  (ResidentialTile)tile;
            if(residentialTile.hasSpaceLeft()) {
                Sleeper sleeper = new Sleeper(gameController, residentialTile);
                residentialTile.addResident(sleeper);
                remove();
                return true;
            }
        }
        return false;
    }

    @Override
    public void dieEffect() {
        gameController.getRegion().slowDown();
    }

    public static void setProperties(Properties engineProperties){
        IMMIGRANT_AGE = Integer.parseInt(engineProperties.getProperty("immigrant.age"));
    }

}
