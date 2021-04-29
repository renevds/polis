package polis.actors;

import polis.GameController;
import polis.tiles.IndustrialTile;
import polis.tiles.ResidentialTile;

import java.util.Properties;

public class Worker extends ActorWithHome{
    private static int WORKER_AGE;
    private IndustrialTile workplace;
    private static int STEPS_PER_GOOD;

    public Worker(GameController gameController, ResidentialTile parentResidential, IndustrialTile workplace) {
        super(gameController, parentResidential, WORKER_AGE);
        this.workplace = workplace;
    }

    @Override
    public void dieEffect() {
        workplace.removeResident(this);
        replaceSelfInParentResidential(new Sleeper(gameController, parentResidential));
        remove();
    }

    @Override
    public void notDeadStep() {
        if((WORKER_AGE - age) % STEPS_PER_GOOD == 0){
            new Good(gameController, workplace);
        }
    }

    public static void setProperties(Properties engineProperties){
        WORKER_AGE = Integer.parseInt(engineProperties.getProperty("worker.age"));
        STEPS_PER_GOOD = Integer.parseInt(engineProperties.getProperty("steps.per.goods"));
    }
}
