import org.junit.Test;

import static junit.framework.Assert.assertTrue;


public class BeerPropertiesGetterTest {
    @Test
    public void shouldLoadBeerNameWhenBeerNameIsInConfigFile(){
        BeerPropertiesGetter getter = new BeerPropertiesGetter("test/config/beer.properties");
        assertTrue(getter.getBeerProperties().getBeerNames().contains("testBeerName"));
    }
}
