package polis;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import polis.Tools.RoadTool;
import polis.Tools.Selector;
import prog2.util.Viewport;


public class polisController {
    gameController GC;

    public StackPane mainPane;
    public Viewport viewPort;
    public Pane gamePane;
    public BorderPane borderPane;

    static int CELL_SIZE= 64;
    static int size = 1;


    public polisController(){
    }

    @FXML
    void initialize(){
        gamePane = new Pane();
        viewPort = new Viewport(gamePane, 0.1);
        mainPane.getChildren().add(viewPort);
        viewPort.setFocusTraversable(true);
        viewPort.toBack();
        borderPane.setPickOnBounds(false);
        GC = new gameController(this);
        viewPort.getStyleClass().add("viewport");
        gamePane.setPrefWidth(CELL_SIZE * 2 * size);
        gamePane.setPrefHeight(CELL_SIZE * size);
        GC.drawTiles();
        GC.createImmigrantRoad();
    }


    public static int getCELLSIZE(){
        return CELL_SIZE;
    }

    public static int getSize(){
        return size;
    }

    public Pane getGamePane(){
        return gamePane;
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

}
