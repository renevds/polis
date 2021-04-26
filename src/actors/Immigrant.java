package actors;

import polis.GameController;
import polis.tiles.MultiTileFiller;
import polis.tiles.ResidentialTile;
import polis.tiles.Street;
import polis.tiles.Tile;
import views.ImmigrantDotView;

public class Immigrant extends MovingActor {

    public Immigrant(int MAX_AGE, GameController gameController, Street tile){
        super(MAX_AGE, gameController, null, tile);
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

    public void step(){
        boolean foundDestination = false;
        for (Tile tile: gameController.getGameGrid().getNeighbours(currentTile)){
            if(isTileDest(tile)){
                foundDestination = true;
                break;
            }
        }
        if(!foundDestination) {
            super.step();
        }
    }

    @Override
    public void dieEffect() {
        gameController.getRegion().slowDown();
    }

}
