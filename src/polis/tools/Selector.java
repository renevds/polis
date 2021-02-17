package polis.tools;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import polis.Tiles.Tile;
import polis.gameController;

public class Selector implements Tool{

    ImageView img;
    gameController GC;
    Pane gamePane;


    public Selector(){
        gamePane = GC.getPC().gamePane;
    }

    public void hover(Tile tile) {
        hideImg();
        createImageOnTile(tile);
    }

    public void hideImg(){
        if (img != null){
            gamePane.getChildren().remove(img);
        }
    }

    public void createImageOnTile(Tile tile){
        img = new ImageView("/polis/buttons/selection.png");
        img.setTranslateX(tile.getRenderX());
        img.setTranslateY(tile.getRenderY());
        GC.getPC().gamePane.getChildren().add(img);
    }

}
