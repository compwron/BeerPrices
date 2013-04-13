import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BeerPropertyTest {
    String beerName = "testBeerName";
    String pricePerOunce = ".50";

    @Test
    public void beerPropertyShouldContainBeerName() {
        BeerProperty beerProperty = new BeerProperty(beerName, pricePerOunce);
        assertThat(beerProperty.beerName, is(beerName));
    }

    @Test
    public void beerPropertyShouldContainPricePerOunce() {
        BeerProperty beerProperty = new BeerProperty(beerName, pricePerOunce);
        assertThat(beerProperty.pricePerOunce, is(pricePerOunce));
    }
}
