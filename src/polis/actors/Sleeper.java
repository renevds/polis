package polis.actors;

import polis.GameController;
import polis.tiles.ResidentialTile;
import polis.tiles.ZoneTile;

import java.util.Properties;

public class Sleeper extends ActorWithHome{

    private static int SLEEPER_AGE;

    public Sleeper(GameController gameController, ResidentialTile parentResidential) {
        super(gameController, parentResidential, SLEEPER_AGE);
    }

    public boolean deleteIfNoHome(){
        if(!gameController.getGameGrid().hasTile(parentResidential)){
            remove();
            return true;
        }
        return false;
    }

    @Override
    public void dieEffect() {
        if(!deleteIfNoHome()) {
            replaceSelfInParentResidential(new Jobseeker(gameController, parentResidential));
            remove();
        }
    }

    @Override
    public void notDeadStep() {

    }

    public static void setProperties(Properties engineProperties){
        SLEEPER_AGE = Integer.parseInt(engineProperties.getProperty("sleeper.age"));
    }

    @Override
    public ActorType getType() {
        return ActorType.SLEEPER;
    }
}
