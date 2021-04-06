package polis.tiles;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import polis.Drawers.Square;
import polis.gameController;
import polis.polisController;

import java.util.ArrayList;
import java.util.List;

public abstract class ZoneTile extends Tile {
    protected static int MAX_LEVEL = 4;
    protected int level;

    protected String imageLink;

    protected ImageView imageView;

    List<ZoneFiller> childrenTiles = new ArrayList<>();

    public ZoneTile(int x, int y, gameController GC) {
        super(x, y, GC);
        level = 1;
        updateImageLink();
    }

    public void addFillerTile(ZoneFiller zoneFiller) {
        childrenTiles.add(zoneFiller);
    }

    public void increaseLevel() {
        level = level % MAX_LEVEL + 1;
        updateImageLink();
        updateImage();
    }

    public void draw() {
        Image img = new Image(imageLink);
        imageView = new ImageView(img);
        fixImageDimensions();
        GC.getPC().getGameGrid().getChildren().add(imageView);
        imageView.setMouseTransparent(true);
        mainNode = Square.drawOnTile(this, GC);
        mainNode.setStyle("-fx-fill: transparent;");
        createEvents(mainNode);
    }

    private void fixImageDimensions() {
        imageView.setTranslateX(getTileRenderX() - polisController.getCELLSIZE() * 2 + polisController.getCELLSIZE() * 4 - imageView.getImage().getWidth());
        imageView.setTranslateY(getTileRenderY() + polisController.getCELLSIZE() * 2 - imageView.getImage().getHeight());
    }

    public abstract void updateImageLink();

    public void updateImage() {
        imageView.setImage(new Image(imageLink));
        fixImageDimensions();
        gameGrid.fixLayers();
    }

    public void remove() {
        for (ZoneFiller zoneFiller : childrenTiles) {
            zoneFiller.remove();
        }
        System.out.println("zone removed");
        GC.getPC().getGameGrid().getChildren().remove(mainNode);
        GC.getPC().getGameGrid().getChildren().remove(imageView);
        System.out.println(GC);
    }

    @Override
    public Boolean removable() {
        return true;
    }

    public void toFront() {
        imageView.toFront();
    }

}
