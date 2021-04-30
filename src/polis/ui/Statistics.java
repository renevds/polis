package polis.ui;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import polis.tiles.*;
import polis.views.StatisticsText;

import java.util.HashSet;

public class Statistics {
    private final HashSet<CommercialTile> commercialTiles;
    private final HashSet<ResidentialTile> residentialTiles;
    private final HashSet<IndustrialTile> industrialTiles;

    private ZoneTile selected;

    final ObservableFraction inhabitans;

    final ObservableFraction jobs;

    final ObservableFraction goods;

    final ObservableFraction customers;

    final SimpleStringProperty title;

    private double inhabitansNumber;
    private double inhabitansMax;

    private double jobsNumber;
    private double jobsMax;

    private double goodsNumber;
    private double goodsMax;

    private double customersNumber;
    private double customersMax;

    //deze klasse houd de statistieken bij

    public Statistics(Pane statisticsPane){
        commercialTiles = new HashSet<>();
        residentialTiles = new HashSet<>();
        industrialTiles = new HashSet<>();

        title = new SimpleStringProperty();

        inhabitans = new ObservableFraction("Bewoners");
        jobs = new ObservableFraction("Jobs");
        goods = new ObservableFraction("Goederen");
        customers = new ObservableFraction("Klanten");

        Text titleText = new Text("STATISTIEKEN");
        titleText.setLayoutY(27);
        titleText.setLayoutX(14);
        titleText.getStyleClass().add("white-text");
        titleText.textProperty().bind(title);

        StatisticsText inhabitanText = new StatisticsText(inhabitans);
        inhabitanText.setLayoutY(47);

        StatisticsText jobsText = new StatisticsText(jobs);
        jobsText.setLayoutY(67);

        StatisticsText goodsText = new StatisticsText(goods);
        goodsText.setLayoutY(87);

        StatisticsText customerText = new StatisticsText(customers);
        customerText.setLayoutY(107);

        statisticsPane.getChildren().add(titleText);
        statisticsPane.getChildren().add(inhabitanText);
        statisticsPane.getChildren().add(jobsText);
        statisticsPane.getChildren().add(goodsText);
        statisticsPane.getChildren().add(customerText);

        calculate();
    }

    public void setSelected(ZoneTile tile){
        selected = tile;
    }


    //berken elke frame
    public void calculate(){

        inhabitansNumber = 0;
        inhabitansMax = 0;

        jobsNumber = 0;
        jobsMax = 0;

        goodsNumber = 0;
        goodsMax = 0;

        customersNumber = 0;
        customersMax = 0;

        if(selected == null){
            title.setValue("STATISTIEKEN");
            for (CommercialTile commercialTile: commercialTiles) {
                addToJobs(commercialTile);
                addToGoods(commercialTile);
                addToCustomers(commercialTile);
            }
            for (IndustrialTile industrialTile: industrialTiles) {
                addToJobs(industrialTile);
            }
            for (ResidentialTile residentialTile: residentialTiles) {
                addToInhabitans(residentialTile);
            }
        }
        else if(selected.getTileType() == Tile.TileType.COMMERCIAL){
            CommercialTile commercialTile = (CommercialTile) selected;
            addToJobs(commercialTile);
            addToGoods(commercialTile);
            addToCustomers(commercialTile);
        }
        else if(selected.getTileType() == Tile.TileType.INDUSTRIAL){
            addToJobs(selected);
        }
        else if(selected.getTileType() == Tile.TileType.RESIDENTIAL){
            addToInhabitans(selected);
        }

        inhabitans.setValue(inhabitansNumber, inhabitansMax);
        jobs.setValue(jobsNumber, jobsMax);
        goods.setValue(goodsNumber, goodsMax);
        customers.setValue(customersNumber, customersMax);

    }

    //functies om tegels te registreren en verwijderen

    private void addToInhabitans(ZoneTile zoneTile){
        inhabitansNumber += zoneTile.getAmountOfResidents();
        inhabitansMax += zoneTile.getCapacity();
    }

    private void addToJobs(ZoneTile zoneTile){
        jobsNumber += zoneTile.getAmountOfResidents();
        jobsMax += zoneTile.getCapacity();
    }

    private void addToJobs(CommercialTile commercialTile){
        jobsNumber += commercialTile.getAmountOfTraders();
        jobsMax += commercialTile.getCapacity();
    }

    private void addToGoods(CommercialTile commercialTile){
        goodsNumber += commercialTile.getAmountOfGoods();
        goodsMax += commercialTile.getGoodCapacity();
    }

    private void addToCustomers(CommercialTile commercialTile){
        customersNumber += commercialTile.getAmountOfResidents();
        customersMax += commercialTile.getCustomerCapacity();
    }


    public void registerCommercial(CommercialTile commercialTile){
        commercialTiles.add(commercialTile);
    }

    public void registerResidential(ResidentialTile residentialTile){
        residentialTiles.add(residentialTile);
    }

    public void registerIndustrial(IndustrialTile industrialTile){
        industrialTiles.add(industrialTile);
    }

    public void removeIndustrialTile(IndustrialTile industrialTile){
        industrialTiles.remove(industrialTile);
    }

    public void removeCommercial(CommercialTile commercialTile){
        commercialTiles.remove(commercialTile);
    }

    public void removeResidential(ResidentialTile residentialTile){
        residentialTiles.remove(residentialTile);
    }

}
