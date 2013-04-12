import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class BeerPropertyGetterTest {
    @Test
    public void getsPropertiesFrom(){
        BeerPropertyGetter getter = new  BeerPropertyGetter("test/config/beer.properties");
        assertThat(getter.getBeerProperties(), is(new BeerProperties()));
    }
}
