import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Properties;

public class BeerList {

    private Properties beerPrices;
    private ArrayList<Beer> allBeers;

    @Autowired
    public BeerList(BeerProperties beerProperties) {
        this.allBeers = makeAllBeers(beerProperties);
    }

    private ArrayList<Beer> makeAllBeers(BeerProperties beerProperties) {
//        for(BeerProperty beerProperty: beerProperties){
//            beers.add(new Beer())
//        }
        return null;
    }

    public void setBeerPrices(Properties beerPrices) {
        this.beerPrices = beerPrices;
    }

    public Properties getBeerPrices() {
        return beerPrices;
    }


    public String getBeerMenu() {
//        StringBuffer message = new StringBuffer;
//        for(Beer beer : beerList.getBeers()){
//            for (Beer.BeerSize beerSize : Beer.BeerSize.values()) {
//               message.append ("The price of a " + beerSize.name() + " of " + beer.beerName + " is " + beer.priceFor(beerSize));
//            }
//        }
        return "";
    }

    public ArrayList<Beer> getLightBeers() {
        return null;
    }
}
