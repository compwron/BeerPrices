import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Properties;

public class BeerList {

    @Autowired
    private SpringTestingObject sto;

    private ArrayList<Beer> allBeers;

    public BeerList(Properties beerProperties) {
        this.allBeers = makeAllBeers(beerProperties);


    }

    private ArrayList<Beer> makeAllBeers(Properties beerProperties) {
        return null;  //To change body of created methods use File | Settings | File Templates.
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
