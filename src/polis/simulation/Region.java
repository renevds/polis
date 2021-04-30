package polis.simulation;

import polis.actors.Immigrant;
import polis.GameController;
import polis.tiles.Street;

import java.util.Properties;
import java.util.Random;

public class Region {
    final private double initial_rate;
    private double tempo;
    final private double factor_recovery;
    final private double factor_slow_down;
    private double timer;
    private final GameController gameController;

    //Deze classe stelt de regio voor een maakt immigranten aan

    public Region(Properties engineProperties, GameController gameController){
        initial_rate = Double.parseDouble(engineProperties.getProperty("region.initial.rate"));
        tempo = Double.parseDouble(engineProperties.getProperty("region.slowest.rate"));
        factor_recovery = Double.parseDouble(engineProperties.getProperty("region.factor.recovery"));
        factor_slow_down = Double.parseDouble(engineProperties.getProperty("region.factor.slow.down"));
        this.gameController = gameController;
    }

    //stap elke frame
    public void step(){
        tempo *= factor_recovery;
        tempo = Math.max(tempo, initial_rate);

        timer -= 1;
        if(timer < 0){
            spawnImmigrant();
            resetTimer();
        }
    }

    private void resetTimer(){
        Random rd = new Random();
        timer = rd.nextDouble()*(tempo - 1);
    }

    private void spawnImmigrant(){
        Street spawnStreet = gameController.getGameGrid().getSpawnStreet();
        Immigrant newImmigrant = new Immigrant(gameController, spawnStreet);
        spawnStreet.addRoadActorAnywhere(newImmigrant);

    }

    public void slowDown(){
        tempo *= factor_slow_down;
    }

}
