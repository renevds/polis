package views;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import polis.gameController;
import polis.polisController;
import polis.tiles.*;

import java.util.ArrayList;
import java.util.List;

public class GameGrid extends Pane {

    private final int MAP_SIZE;
    private final List<Tile> tiles;
    private final List<BackgroundTile> backgroundTiles;
    private final gameController GC;

    public GameGrid(gameController GC, int MAP_SIZE) {
        this.MAP_SIZE = MAP_SIZE;
        tiles = new ArrayList<>();
        backgroundTiles = new ArrayList<>();
        this.GC = GC;

        InnerShadow innerShadow = new InnerShadow();
        innerShadow.setOffsetX(0);
        innerShadow.setOffsetY(0);
        innerShadow.setRadius(128);
        innerShadow.setColor(Color.web("#b1c6ca"));
        setEffect(innerShadow);
    }

    public void createTiles() {
        for (int x = 1; x <= MAP_SIZE; x++) {
            for (int y = 1; y <= MAP_SIZE; y++) {
                Tile temp = new StandardTile(x, y, GC);
                tiles.add(temp);
            }
        }
    }


    public void createImmigrantRoad() {
        for (int i = 1; i <= 16; i++) {
            Street immigrantRoad = new Street(16, i, GC);
            replaceTile(immigrantRoad);
            immigrantRoad.makeUnRemovable();
            immigrantRoad.calculateOrientationNumber(true);
        }

    }

    public void drawBackgroundTiles() {
        for (int x = 1; x <= MAP_SIZE; x++) {
            for (int y = 1; y <= MAP_SIZE; y++) {
                backgroundTiles.add(new BackgroundTile(x, y, this));
            }
        }
    }

    public void askForRegen(Event event) {
        Boolean regen = true;
        while (regen) {
            Alert alert = new Alert(Alert.AlertType.NONE, "Would you like to generate a different map?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();
            regen = (alert.getResult() == ButtonType.YES);
            if (regen) {
                regenBackgroundTiles();
            }
        }
    }

    public void regenBackgroundTiles() {
        BackgroundTile.regenNoise(this);
        for (int x = 1; x <= MAP_SIZE; x++) {
            for (int y = 1; y <= MAP_SIZE; y++) {
                int index = coordToIndex(x, y);
                removeChildren(backgroundTiles.get(index));
                backgroundTiles.set(index, new BackgroundTile(x, y, this));
            }
        }
        createImmigrantRoad();
    }

    public int coordToIndex(int x, int y) {
        return (x - 1) * MAP_SIZE + y - 1;
    }


    public void replaceTile(Tile newTile) {
        int x = newTile.getX();
        int y = newTile.getY();
        Tile oldtile = getTileAtCoord(x, y);
        oldtile.remove();
        tiles.set(coordToIndex(x, y), newTile);
        getBackgroundTileBehindTile(newTile).clear();
        fixLayers();
    }

    public void replaceMultiTile(MultiTile newTile) {
        int x = newTile.getX();
        int y = newTile.getY();
        for (int dx = 0; dx < newTile.getWidth(); dx++) {
            for (int dy = 0; dy < newTile.getHeight(); dy++) {
                if (dx == 0 && dy == 0) {
                    replaceTile(newTile);
                } else {
                    replaceTile(new MultiTileFiller(x + dx, y + dy, GC, newTile));
                }
            }
        }
    }

    public Tile getTileAtCoord(int x, int y) {
        return tiles.get(coordToIndex(x, y));
    }

    public BackgroundTile getBackgroundTileBehindTile(Tile tile) {
        return backgroundTiles.get(coordToIndex(tile.getX(), tile.getY()));
    }

    public boolean validCoord(int coord) {
        return coord > 0 && coord <= MAP_SIZE;
    }

    public void setTile(Tile newTile) {
        int x = newTile.getX();
        int y = newTile.getY();
        tiles.set(coordToIndex(x, y), newTile);
    }


    public void fixLayers() {
        for (int a = 1; a < MAP_SIZE * 2; a++) {
            int Da = Math.min(a, MAP_SIZE);
            int Dy = Math.max(1, a - MAP_SIZE + 1);
            for (int i = 0; i < Da && Dy + i <= MAP_SIZE; i++) {
                int x = Da - i;
                int y = Dy + i;
                fixCoordLayer(x, y);
            }
        }
    }

    public void fixCoordLayer(int x, int y) {
        Tile tile = getTileAtCoord(x, y);
        if (!(tile instanceof MultiTileFiller)) {
            getBackgroundTileBehindTile(tile).toFront();
        }
        tile.toFront();
    }

    public double getRenderY(int x, int y) {
        return (double) polisController.getCELLSIZE() * (y + x) / 2 - polisController.getCELLSIZE() / 2.0;
    }

    public double getRenderX(int x, int y) {
        return -((double) polisController.getCELLSIZE() * MAP_SIZE) / 2 + polisController.getCELLSIZE() * (1 - y + x);
    }

    public void addChildrenToGrid(Node node, int x, int y, double XOffset, double YOffset) {
        getChildren().add(node);
        node.setTranslateX(getRenderX(x, y) - XOffset);
        node.setTranslateY(getRenderY(x, y) - YOffset);
    }

    public void removeChildren(Node node) {
        getChildren().remove(node);
    }

    public void addChildrenToGrid(Node node, int x, int y) {
        addChildrenToGrid(node, x, y, 0, 0);
    }

    public int getMAP_SIZE() {
        return MAP_SIZE;
    }

    ;
}
