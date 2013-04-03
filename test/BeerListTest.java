import org.junit.Test;

import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class BeerListTest {
    @Test
    public void getsWholeBeerMenu(){
        Properties prop = mock(Properties.class);
        BeerList beerList = new BeerList(prop);
        assertEquals("", beerList.getBeerMenu());
    }


}
