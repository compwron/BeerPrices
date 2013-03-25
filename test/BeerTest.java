import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class BeerTest {
    @Test
    public void getsBeerBasePrice() {
        Beer beer = new Beer("bottle", "shiner");
        assertEquals(1.50, beer.getBasePrice());
    }

    @Test
    public void getsBeerShelfPriceWhenInABottle() {
        Beer beer = new Beer("bottle", "budweiser");
        assertEquals(3.00, beer.calculateShelfPrice());
    }

}
