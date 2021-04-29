package polis.ui;

import javafx.beans.property.SimpleStringProperty;

public class ObservableFraction extends SimpleStringProperty {

    String title;

    public ObservableFraction(String title){
        super();
        this.title = title;
    }

    private double roundToOneDecimal(Double a){
        return Math.round(a*10)/10;
    }

    public void setValue(Double a, Double b) {
        if(a == 0 && b == 0){
                setValue("");
        }
        else {
            setValue(title + ": " + roundToOneDecimal(a) + " / " + roundToOneDecimal(b));
        }
    }


}
