import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class BeerPropertiesTest {
    @Test
    public void shouldContainNamesOfAllBeersInConfig() {
        BeerProperties beerProperties = new BeerPropertiesGetter("test/config/beer.properties").getBeerProperties();
        ArrayList<String> expectedBeerNames = new ArrayList<String>();
        expectedBeerNames.add("testBeerName");

        assertEquals(expectedBeerNames, beerProperties.getBeerNames());
    }

    @Test
    public void beerPropertiesShouldContainAsManyBeerPropertyObjectsAsThereAreBeersInConfig() {
        BeerProperties beerProperties = new BeerPropertiesGetter("test/config/beer.properties").getBeerProperties();
        assertThat(beerProperties.allBeerProperties().size(), is(1));
    }
}
