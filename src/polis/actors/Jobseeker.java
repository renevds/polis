package polis.actors;

import polis.GameController;
import polis.tiles.*;
import polis.views.JobseekerDotView;

import java.util.Properties;

public class Jobseeker extends MovingActor {

    private static int JOBSEEKER_AGE;

    protected Jobseeker(GameController gameController, ResidentialTile homeTile) {
        super(JOBSEEKER_AGE, gameController, homeTile);

        this.view = new JobseekerDotView(this, currentStreet);

        System.out.println("job");
    }

    @Override
    protected boolean isTileDest(Tile tile) {
        if(tile instanceof MultiTileFiller){
            tile = ((MultiTileFiller) tile).getParentZone();
        }
        if(tile instanceof CommercialTile){
            CommercialTile commercialTile =  (CommercialTile)tile;
            if(commercialTile.canTakeTrader()) {
                parentResidential.jobFound();
                Trader trader = new Trader(gameController, parentResidential, commercialTile);
                replaceSelfInParentResidential(trader);
                remove();
                return true;
            }

        }
        else if(tile instanceof IndustrialTile){
            IndustrialTile industrialTile =  (IndustrialTile)tile;
            if(industrialTile.hasSpaceLeft()) {
                Worker worker = new Worker(gameController, parentResidential, industrialTile);
                replaceSelfInParentResidential(worker);
                industrialTile.addResident(worker);
                parentResidential.jobFound();
                remove();
                return true;
            }
        }
        return false;
    }

    @Override
    public void dieEffect() {
        replaceSelfInParentResidential(new Sleeper(gameController, parentResidential));
        parentResidential.jobNotFound();
    }

    public static void setProperties(Properties engineProperties){
        JOBSEEKER_AGE = Integer.parseInt(engineProperties.getProperty("jobseeker.age"));
    }

}
