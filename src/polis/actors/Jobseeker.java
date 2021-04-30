package polis.actors;

import polis.GameController;
import polis.tiles.*;
import polis.views.JobseekerDotView;

import java.util.Properties;

public class Jobseeker extends MovingActor {

    private static int JOBSEEKER_AGE;

    protected Jobseeker(GameController gameController, ResidentialTile parentResidential) {
        super(JOBSEEKER_AGE, gameController, parentResidential);
        view = new JobseekerDotView(this, currentStreet);
    }

    @Override
    protected boolean isTileDest(Tile tile) {
        tile = tile.getParentTile();
        if(tile.acceptsResident(this)){
            parentResidential.jobFound();
            remove();
            return true;
        }
        return false;
    }

    @Override
    public void dieEffect() {
        replaceSelfInParentResidential(new Sleeper(gameController, parentResidential));
        parentResidential.jobNotFound();
        remove();
    }

    public static void setProperties(Properties engineProperties){
        JOBSEEKER_AGE = Integer.parseInt(engineProperties.getProperty("jobseeker.age"));
    }

    @Override
    public ActorType getType() {
        return ActorType.JOBSEEKER;
    }

}
