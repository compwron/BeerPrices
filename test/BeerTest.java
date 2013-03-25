import org.junit.Test;

import static junit.framework.Assert.assertTrue;

public class BeerTest {
    @Test
    public void getsBeerBasePrice() {
        Beer beer = new Beer("bottle", "Shiner");
        assertTrue(0.99 == beer.getBasePrice());
    }
}
