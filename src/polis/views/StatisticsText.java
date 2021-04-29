package polis.views;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.StringProperty;
import javafx.scene.text.Text;
import polis.ui.ObservableFraction;

public class StatisticsText extends Text implements InvalidationListener {

    ObservableFraction observableFraction;

    public StatisticsText(ObservableFraction observableFraction){
        super();
        setText(observableFraction.toString());
        observableFraction.addListener(this);
        this.observableFraction = observableFraction;
        getStyleClass().add("white-text");
        setLayoutX(14);
    }

    @Override
    public void invalidated(Observable observable) {
        setText(observableFraction.get());
    }
}
