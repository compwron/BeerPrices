import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BeerPropertyTest {
    String beerName = "testBeerName";
    Double pricePerOunce = 0.50;
    Double baseBeerPrice = 1.50;

    @Test
    public void beerPropertyShouldContainBeerName() {
        BeerProperty beerProperty = new BeerProperty(properties());
        assertThat(beerProperty.beerName, is(beerName));
    }

    @Test
    public void beerPropertyShouldContainPricePerOunce() {
        BeerProperty beerProperty = new BeerProperty(properties());
        assertThat(beerProperty.pricePerOunce, is(pricePerOunce));
    }

    @Test
    public void beerPropertyShouldContainBasePrice() {
        BeerProperty beerProperty = new BeerProperty(properties());
        assertThat(beerProperty.basePrice, is(baseBeerPrice));
    }

    private ArrayList<String> properties() {
        ArrayList<String> properties = new ArrayList<String>();
        properties.add("base." + beerName + "=" + baseBeerPrice);
        properties.add("ounce." + beerName + "=" + pricePerOunce);
        return properties;
    }
}
