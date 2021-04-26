package simulation;

import actors.Actor;
import actors.Immigrant;
import polis.GameController;
import polis.tiles.Street;

import java.util.Properties;
import java.util.Random;

public class Region {
    final private double initial_rate;
    private double tempo;
    final private double factor_recovery;
    final private double factor_slow_down;
    final private int immigrant_age;
    private double timer;
    GameController gameController;

    public Region(Properties engineProperties, GameController gameController){
        initial_rate = Double.parseDouble(engineProperties.getProperty("region.initial.rate"));
        tempo = Double.parseDouble(engineProperties.getProperty("region.slowest.rate"));
        factor_recovery = Double.parseDouble(engineProperties.getProperty("region.factor.recovery"));
        factor_slow_down = Double.parseDouble(engineProperties.getProperty("region.factor.slow.down"));
        immigrant_age = Integer.parseInt(engineProperties.getProperty("immigrant.age"));
        resetTimer();
        this.gameController = gameController;
    }

    public void step(){
        tempo *= factor_recovery;
        tempo = Math.max(tempo, initial_rate);

        timer -= 1;
        if(timer < 0){
            spawnImmigrant();
            resetTimer();
        }
    }

    public void resetTimer(){
        Random rd = new Random();
        timer = rd.nextDouble()*(tempo - 1);
    }

    public void spawnImmigrant(){
        Street spawnStreet = gameController.getGameGrid().getSpawnStreet();
        Actor newImmigrant = new Immigrant(immigrant_age, gameController, spawnStreet);
        spawnStreet.addRoadActorAnywhere(newImmigrant);

    }

    public void slowDown(){
        tempo *= factor_slow_down;
    }

}
