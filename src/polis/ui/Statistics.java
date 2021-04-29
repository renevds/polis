package polis.ui;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.layout.Pane;
import polis.tiles.CommercialTile;
import polis.tiles.IndustrialTile;
import polis.tiles.ResidentialTile;
import polis.tiles.Tile;
import polis.views.StatisticsText;

import java.util.HashSet;

public class Statistics {
    private HashSet<CommercialTile> commercialTiles;
    private HashSet<ResidentialTile> residentialTiles;
    private HashSet<IndustrialTile> industrialTiles;

    private Tile selected;

    private Pane statisticsPane;

    ObservableFraction inhabitans;

    ObservableFraction jobs;

    ObservableFraction goods;

    ObservableFraction customers;

    private double inhabitansNumber;
    private double inhabitansMax;

    private double jobsNumber;
    private double jobsMax;

    private double goodsNumber;
    private double goodsMax;

    private double customersNumber;
    private double customersMax;


    public Statistics(Pane statisticsPane){
        commercialTiles = new HashSet<>();
        residentialTiles = new HashSet<>();
        industrialTiles = new HashSet<>();
        this.statisticsPane = statisticsPane;

        inhabitans = new ObservableFraction("Bewoners");
        jobs = new ObservableFraction("Jobs");
        goods = new ObservableFraction("Goederen");
        customers = new ObservableFraction("Klanten");

        StatisticsText inhabitanText = new StatisticsText(inhabitans);
        inhabitanText.setLayoutY(47);

        StatisticsText jobsText = new StatisticsText(jobs);
        jobsText.setLayoutY(67);

        StatisticsText goodsText = new StatisticsText(goods);
        goodsText.setLayoutY(87);

        StatisticsText customerText = new StatisticsText(customers);
        customerText.setLayoutY(107);

        statisticsPane.getChildren().add(inhabitanText);
        statisticsPane.getChildren().add(jobsText);
        statisticsPane.getChildren().add(goodsText);
        statisticsPane.getChildren().add(customerText);

        calculate();
    }

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
            for (CommercialTile commercialTile: commercialTiles) {
                addToJobs(commercialTile);
                addToGoods(commercialTile);
            }
            for (IndustrialTile industrialTile: industrialTiles) {
                addToJobs(industrialTile);
            }
            for (ResidentialTile residentialTile: residentialTiles) {
                addToInhabitans(residentialTile);
            }
        }

        inhabitans.setValue(inhabitansNumber, inhabitansMax);
        jobs.setValue(jobsNumber, jobsMax);
        goods.setValue(goodsNumber, goodsMax);
        customers.setValue(customersNumber, customersMax);

    }

    private void addToInhabitans(ResidentialTile residentialTile){
        inhabitansNumber += residentialTile.getAmountOfResidents();
        inhabitansMax += residentialTile.getCapacity();
    }

    private void addToJobs(IndustrialTile industrialTile){
        jobsNumber += industrialTile.getAmountOfResidents();
        jobsMax += industrialTile.getCapacity();
    }

    private void addToJobs(CommercialTile commercialTile){
        jobsNumber += commercialTile.getAmountOfTraders();
        jobsMax += commercialTile.getCapacity();
    }

    private void addToGoods(CommercialTile commercialTile){
        goodsNumber += commercialTile.getAmountOfGoods();
        goodsMax += commercialTile.getCapacity();
    }

    private void addToCustomers(CommercialTile commercialTile){
        customersNumber += commercialTile.getAmountOfGoods();
        customersMax += commercialTile.getCapacity();
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
