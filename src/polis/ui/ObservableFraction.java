package polis.ui;

import javafx.beans.property.SimpleStringProperty;

public class ObservableFraction extends SimpleStringProperty {

    String title;

    public ObservableFraction(String title){
        super();
        this.title = title;
    }

    public void setValue(Double a, Double b) {
        super.setValue(title + ": " + a + " / " + b);
    }
}
