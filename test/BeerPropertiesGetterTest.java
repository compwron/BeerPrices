import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class BeerPropertiesGetterTest {
    @Test
    public void getsPropertiesFrom(){
        BeerPropertiesGetter getter = new BeerPropertiesGetter("test/config/beer.properties");
        assertThat(getter.getBeerProperties(), is(new BeerProperties()));
    }
}
