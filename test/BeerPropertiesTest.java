import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class BeerPropertiesTest {
    @Test
    public void shouldLoadPropertiesFromFile() {
        BeerProperties beerProperties = new BeerProperties("test/config/beer.properties");
        ArrayList<String> expectedBeerNames = new ArrayList<String>();
        expectedBeerNames.add("testBeerName");

        assertEquals(expectedBeerNames, beerProperties.getBeerNames());
    }

    @Test
    public void knowsBasePriceOfBeer() {

    }
}
