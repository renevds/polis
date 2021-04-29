package polis;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private GameController gameController;

    public StackPane mainPane;
    private Viewport viewPort;
    private GameGrid gameGrid;
    public BorderPane borderPane;

    public ToggleButton residential_button;
    public ToggleButton industrial_button;
    public ToggleButton commercial_button;
    public ToggleButton road_button;
    public ToggleButton delete_button;
    public ToggleButton select_button;
    public ImageView pausePlayButtonImage;

    public Pane statisticsPane;

    private static final int CELL_SIZE= 64;

    Image play;
    Image pause;

    public PolisController(){
    }

    @FXML
    public void initialize() throws IOException {
        int MAP_SIZE = 32;
        gameController = new GameController(this);
        gameGrid = new GameGrid(gameController, MAP_SIZE);
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

        gameController.setGameGrid(gameGrid);
        gameController.setTool(new Selector(gameController));

        play = new Image("polis/buttons/play.png");
        pause = new Image("polis/buttons/pause.png");
        pausePlayButtonImage.setImage(pause);
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
        gameController.setTool(new Selector(gameController));
    }

    @FXML
    public void roadButton(){
        gameController.setTool(new RoadTool(gameController));
    }

    @FXML
    public void deleteButton(){
        gameController.setTool(new DeleteTool(gameController));
    }

    @FXML
    public void buildResidentialButton(){
        gameController.setTool(new ResidentialTool(gameController));
    }

    @FXML
    public void buildIndustrialButton(){
        gameController.setTool(new IndustrialTool(gameController));
    }

    @FXML
    public void buildCommercialButton(){
        gameController.setTool(new CommercialTool(gameController));
    }

    @FXML
    public void buildHelicopterButton(){
        gameController.setTool(new HelicopterTool(gameController));
    }

    @FXML
    public void buildTreeButton(){
        gameController.setTool(new TreeTool(gameController));
    }

    @FXML
    public void buildWaterButton(){
        gameController.setTool(new WaterTool(gameController));
    }

    @FXML
    public void pausePlay(){
        if(pausePlayButtonImage.getImage() == play){
            pausePlayButtonImage.setImage(pause);
        }
        else {
            pausePlayButtonImage.setImage(play);
        }
        gameController.pausePlay();
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
