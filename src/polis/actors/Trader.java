package polis.actors;

import polis.GameController;
import polis.tiles.ResidentialTile;

import java.util.Properties;

public class Trader extends ActorWithHome{

    private static int MAX_AGE;

    public Trader(GameController gameController, ResidentialTile parentResidential) {
        super(gameController, parentResidential, MAX_AGE);
    }

    @Override
    protected void dieEffect() {

    }

    @Override
    protected void notDeadStep() {
    }

    public static void setProperties(Properties engineProperties){
        MAX_AGE = Integer.parseInt(engineProperties.getProperty("trader.age"));
    }
}
