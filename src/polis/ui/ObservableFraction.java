package polis.ui;

import javafx.beans.property.SimpleStringProperty;

public class ObservableFraction extends SimpleStringProperty {

    final String title;

    //simpele uitbereiding van de observable SimpleStringProperty voor de fracties in de statistiekenn

    public ObservableFraction(String title){
        super();
        this.title = title;
    }

    private double roundToOneDecimal(Double a){
        return Math.round(a*10.0)/10.0;
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
