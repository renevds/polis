package polis;

import polis.actors.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import polis.tiles.*;
import polis.Tools.Tool;
import polis.simulation.Region;
import polis.ui.Statistics;
import polis.views.GameGrid;

import java.io.IOException;
import java.util.*;

public class GameController {
    private GameGrid gameGrid;
    private PolisController PC;
    private Tool tool;
    private Tile lastHoverTile;
    private final Properties engineProperties;
    private final Properties levelsProperties;
    private Region region;
    private Set<Actor> actors;
    private Set<Actor> actorsToBeRemoved;
    private Set<Actor> actorsToBeAdded;
    private Statistics statistics;
    private Timeline timeline;
    boolean playing;

    public GameController(PolisController PC) throws IOException {
        this.PC = PC;
        engineProperties = new Properties();
        engineProperties.load(getClass().getClassLoader().getResourceAsStream("polis/engine.properties"));
        levelsProperties = new Properties();
        levelsProperties.load(getClass().getClassLoader().getResourceAsStream("polis/levels.properties"));
        region = new Region(engineProperties, this);
        actors = new HashSet<>();
        actorsToBeRemoved = new HashSet<>();
        actorsToBeAdded = new HashSet<>();

        setProperties();

        timeline = new Timeline(new KeyFrame(Duration.seconds(0.250), e -> step()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.playFromStart();
        statistics = new Statistics(PC.getStatisticsPane());
        playing = true;
    }

    public void setGameGrid(GameGrid gameGrid) {
        this.gameGrid = gameGrid;
    }

    public GameGrid getGameGrid() {
        return gameGrid;
    }

    public PolisController getPC() {
        return PC;
    }

    public void setTool(Tool tool) {
        if (this.tool != null) {
            this.tool.close();
        }
        this.tool = tool;
        if(lastHoverTile != null) {
            tool.hover(lastHoverTile);
        }
    }

    public Region getRegion() {
        return region;
    }

    public void setCurrentHover(Tile tile) {
        lastHoverTile = tile;
        tool.hover(tile);
    }

    public void addActor(Actor actor){
        actorsToBeAdded.add(actor);
    }

    public void removeActor(Actor actor){
        actorsToBeRemoved.add(actor);
    }

    private void step(){
        region.step();
        for(Tile tile: gameGrid.getTiles()){
            tile.step();
        }
        for (Actor actor: actors){
            actor.step();
        }
        actors.addAll(actorsToBeAdded);
        actorsToBeAdded.clear();
        actors.removeAll(actorsToBeRemoved);
        actorsToBeRemoved.clear();
        tool.toFront();
        statistics.calculate();
    }

    public void setClicked(Tile tile) {
        tool.clicked(tile);
    }

    public void setDrag(Tile tile) {
        tool.drag(tile);
    }

    public void setRelease(Tile tile) {
        tool.release(tile);
    }

    private void setProperties(){
        ResidentialTile.setProperties(engineProperties, levelsProperties);
        IndustrialTile.setProperties(engineProperties, levelsProperties);
        CommercialTile.setProperties(engineProperties, levelsProperties);

        Immigrant.setProperties(engineProperties);
        Good.setProperties(engineProperties);
        Jobseeker.setProperties(engineProperties);
        Shopper.setProperties(engineProperties);

        Sleeper.setProperties(engineProperties);
        Worker.setProperties(engineProperties);
        Trader.setProperties(engineProperties);
        Customer.setProperties(engineProperties);
    }

    public Statistics getStatistics(){
        return statistics;
    }

    public void pausePlay(){
        if(playing){
           timeline.pause();
        }
        else {
            timeline.play();
        }
        playing = !playing;
    }
}
