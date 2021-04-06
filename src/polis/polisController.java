package polis;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import polis.Drawers.Square;
import polis.Tools.DeleteTool;
import polis.Tools.MultiTileBuilder;
import polis.Tools.RoadTool;
import polis.Tools.Selector;
import polis.tiles.Tile;
import prog2.util.Viewport;
import views.GameGrid;


public class polisController {
    private gameController GC;

    public StackPane mainPane;
    public Viewport viewPort;
    private GameGrid gameGrid;
    public BorderPane borderPane;

    public ToggleButton residential_button;
    public ToggleButton industrial_button;
    public ToggleButton commercial_button;
    public ToggleButton road_button;
    public ToggleButton delete_button;
    public ToggleButton select_button;

    private static int CELL_SIZE= 64;
    private static int size = 1;


    public polisController(){
    }

    @FXML
    void initialize(){
        GC = new gameController(this);
        gameGrid = new GameGrid(GC, 32);
        viewPort = new Viewport(gameGrid, 0.6);
        mainPane.getChildren().add(viewPort);
        mainPane.setOnKeyPressed(this::handleKeyPressed);
        viewPort.setFocusTraversable(true);
        viewPort.toBack();
        borderPane.setPickOnBounds(false);
        viewPort.getStyleClass().add("viewport");
        mainPane.setPrefWidth(CELL_SIZE * 2 * size * 10);
        mainPane.setPrefHeight(CELL_SIZE * size * 10);

        //draw the green background
        for(int x = 1; x <= gameGrid.getMAP_SIZE(); x++){
            for(int y = 1; y <= gameGrid.getMAP_SIZE(); y++){
                /*Polygon polygon = Square.draw();
                polygon.setTranslateX(gameGrid.getRenderX(x, y));
                polygon.setTranslateY(gameGrid.getRenderY(x, y));
                polygon.getStyleClass().add("background-tile");
                polygon.setMouseTransparent(true);
                polygon.toBack();
                gameGrid.getChildren().add(polygon);*/

                ImageView iv = new ImageView("polis/tiles/grass.png");
                gameGrid.getChildren().add(iv);
                iv.setTranslateX(gameGrid.getRenderX(x, y) - 64);
                iv.setTranslateY(gameGrid.getRenderY(x, y));
                iv.toBack();
            }
        }

        InnerShadow innerShadow = new InnerShadow();
        innerShadow.setOffsetX(0);
        innerShadow.setOffsetY(0);
        innerShadow.setRadius(128);
        innerShadow.setColor(Color.web("#b1c6ca"));
        gameGrid.setEffect(innerShadow);

        gameGrid.createTiles();
        gameGrid.drawTiles();
        gameGrid.createImmigrantRoad();

        GC.setGameGrid(gameGrid);
        GC.setTool(new Selector(GC));

    }

    public static int getCELLSIZE(){
        return CELL_SIZE;
    }

    public static int getSize(){
        return size;
    }

    public GameGrid getGameGrid(){
        return gameGrid;
    }

    @FXML
    void selectButton(){
        GC.setTool(new Selector(GC));
        System.out.println("selector tool chosen");
    }

    @FXML
    void roadButton(){
        GC.setTool(new RoadTool(GC));
        System.out.println("Road tool chosen");
    }

    @FXML
    void deleteButton(){
        GC.setTool(new DeleteTool(GC));
        System.out.println("Delete tool chosen");
    }

    @FXML
    void buildResidentialButton(){
        GC.setTool(new MultiTileBuilder(GC, "residential"));
        System.out.println("Residential tool chosen");
    }

    @FXML
    void buildIndustrialButton(){
        GC.setTool(new MultiTileBuilder(GC, "industrial"));
        System.out.println("Industrial tool chosen");
    }

    @FXML
    void buildCommercialButton(){
        GC.setTool(new MultiTileBuilder(GC, "commercial"));
        System.out.println("Commercial tool chosen");
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
    };
}
