package polis;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import polis.Tools.*;
import polis.tiles.Tile;
import prog2.util.Viewport;
import views.GameGrid;


public class polisController {
    private gameController GC;

    public StackPane mainPane;
    public Viewport viewPort;
    private GameGrid gameGrid;
    public BorderPane borderPane;
    private Tile lastHoverTile;

    public ToggleButton residential_button;
    public ToggleButton industrial_button;
    public ToggleButton commercial_button;
    public ToggleButton road_button;
    public ToggleButton delete_button;
    public ToggleButton select_button;

    private static final int CELL_SIZE= 64;


    public polisController(){
    }

    @FXML
    void initialize(){
        int MAP_SIZE = 32;
        GC = new gameController(this);
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
        stage.setOnShown(gameGrid::askForRegen);
    }

    public static int getCELLSIZE(){
        return CELL_SIZE;
    }

    public GameGrid getGameGrid(){
        return gameGrid;
    }

    @FXML
    void selectButton(){
        GC.setTool(new Selector(GC));
    }

    @FXML
    void roadButton(){
        GC.setTool(new RoadTool(GC));
    }

    @FXML
    void deleteButton(){
        GC.setTool(new DeleteTool(GC));
    }

    @FXML
    void buildResidentialButton(){
        GC.setTool(new MultiTileBuilder(GC, "residential"));
    }

    @FXML
    void buildIndustrialButton(){
        GC.setTool(new MultiTileBuilder(GC, "industrial"));
    }

    @FXML
    void buildCommercialButton(){
        GC.setTool(new MultiTileBuilder(GC, "commercial"));
    }

    @FXML
    void buildHelicopterButton(){
        GC.setTool(new MultiTileBuilder(GC, "helicopter"));
    }

    @FXML
    void buildTreeButton(){
        GC.setTool(new TreeTool(GC));
    }

    @FXML
    void buildWaterButton(){
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
