package polis;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import polis.Tools.*;
import polis.tiles.Tile;
import prog2.util.Viewport;
import polis.views.GameGrid;

import java.io.IOException;


public class PolisController {
    private GameController GC;

    public StackPane mainPane;
    private Viewport viewPort;
    private GameGrid gameGrid;
    public BorderPane borderPane;
    public Tile lastHoverTile;

    public ToggleButton residential_button;
    public ToggleButton industrial_button;
    public ToggleButton commercial_button;
    public ToggleButton road_button;
    public ToggleButton delete_button;
    public ToggleButton select_button;

    public Pane statisticsPane;

    private static final int CELL_SIZE= 64;


    public PolisController(){
    }

    @FXML
    public void initialize() throws IOException {
        int MAP_SIZE = 32;
        GC = new GameController(this);
        gameGrid = new GameGrid(GC, MAP_SIZE);
        viewPort = new Viewport(gameGrid, 0.3);
        mainPane.getChildren().add(viewPort);
        mainPane.setOnKeyPressed(this::handleKeyPressed);
        viewPort.setFocusTraversable(true);
        viewPort.toBack();
        borderPane.setPickOnBounds(false);
        viewPort.getStyleClass().add("viewport");

        mainPane.setPrefWidth(CELL_SIZE * 2 * 10);
        mainPane.setPrefHeight(CELL_SIZE * 10);
        gameGrid.setTranslateX(gameGrid.getTranslateX() + MAP_SIZE*10);
        gameGrid.setTranslateY(gameGrid.getTranslateY() - MAP_SIZE*5);

        gameGrid.createTiles();
        gameGrid.drawBackgroundTiles();
        gameGrid.createImmigrantRoad();

        GC.setGameGrid(gameGrid);
        GC.setTool(new Selector(GC));
    }

    public void setStage(Stage stage){
        //stage.setOnShown(gameGrid::askForRegen);
    }

    public Pane getStatisticsPane(){
        return statisticsPane;
    }

    public static int getCELLSIZE(){
        return CELL_SIZE;
    }

    public GameGrid getGameGrid(){
        return gameGrid;
    }

    @FXML
    public void selectButton(){
        GC.setTool(new Selector(GC));
    }

    @FXML
    public void roadButton(){
        GC.setTool(new RoadTool(GC));
    }

    @FXML
    public void deleteButton(){
        GC.setTool(new DeleteTool(GC));
    }

    @FXML
    public void buildResidentialButton(){
        GC.setTool(new MultiTileBuilder(GC, "residential"));
    }

    @FXML
    public void buildIndustrialButton(){
        GC.setTool(new MultiTileBuilder(GC, "industrial"));
    }

    @FXML
    public void buildCommercialButton(){
        GC.setTool(new MultiTileBuilder(GC, "commercial"));
    }

    @FXML
    public void buildHelicopterButton(){
        GC.setTool(new MultiTileBuilder(GC, "helicopter"));
    }

    @FXML
    public void buildTreeButton(){
        GC.setTool(new TreeTool(GC));
    }

    @FXML
    public void buildWaterButton(){
        GC.setTool(new WaterTool(GC));
    }

    private void handleKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case R:
                residential_button.setSelected(true);
                buildResidentialButton();
                break;
            case I:
                industrial_button.setSelected(true);
                buildIndustrialButton();
                break;
            case C:
                commercial_button.setSelected(true);
                buildCommercialButton();
                break;
            case S:
                road_button.setSelected(true);
                roadButton();
                break;
            case B:
                delete_button.setSelected(true);
                deleteButton();
                break;
            case ESCAPE:
                select_button.setSelected(true);
                selectButton();
                break;
        }
    }
}
