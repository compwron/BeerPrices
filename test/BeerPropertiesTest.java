import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class BeerPropertiesTest {
    @Test
    public void shouldLoadPropertiesFromFile(){
        BeerProperties beerProperties = new BeerProperties("config/beer.properties");
        ArrayList<String> expectedBeerNames = new ArrayList<String>();
        expectedBeerNames.add("testBeerName");

        assertEquals(expectedBeerNames, beerProperties.getBeerNames());
    }
}