package polis.actors;

import polis.GameController;
import polis.tiles.ResidentialTile;

import java.util.Properties;

public class Sleeper extends ActorWithHome{

    private static int SLEEPER_AGE;

    public Sleeper(GameController gameController, ResidentialTile parentResidential) {
        super(gameController, parentResidential, SLEEPER_AGE);
    }

    @Override
    public void dieEffect() {
        replaceSelfInParentResidential(new Jobseeker(gameController, parentResidential));
        remove();
    }

    @Override
    public void notDeadStep() {

    }

    public static void setProperties(Properties engineProperties){
        SLEEPER_AGE = Integer.parseInt(engineProperties.getProperty("sleeper.age"));
    }

}
