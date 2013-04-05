import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Properties;

public class BeerList {

    private Properties beerPrices;
    private ArrayList<Beer> allBeers;

    @Autowired
    public BeerList(Properties beerPrices) {
        this.allBeers = makeAllBeers(beerPrices);
    }

    private ArrayList<Beer> makeAllBeers(Properties beerProperties) {
        return null;
    }

    public void setBeerPrices(Properties beerPrices){
        this.beerPrices = beerPrices;
    }

    public Properties getBeerPrices(){
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
}
