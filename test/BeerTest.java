import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class BeerTest {
    @Test
    public void getsBeerBasePrice() {
        Beer beer = new Beer("bottle", "shiner");
        assertEquals("1.50", beer.getBasePrice());
    }

    @Test
    public void readsAllProperties() {
        Beer beer = new Beer("bottle", "shiner");
        assertEquals("corona => 1.30\nbudweiser => 1.00\nshiner => 1.50\nstella => 1.40\n", beer.getsAllProperties());
    }

}
