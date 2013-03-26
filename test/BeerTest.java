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

    @Test
    public void getsBeerShelfPriceWhenFromAKeg() {
        Beer beer = new Beer("8", "budweiser");
        assertEquals(9.00, beer.calculateShelfPrice());
    }

//    @Test(expected=NumberFormatException.class)
//    public void throwsExeptionWhenGivenNonsenseSize() throws NumberFormatException {
//        Beer beer = new Beer("happy", "shiner");
//        try { beer.calculateShelfPrice(); }
//        catch (NumberFormatException e) {
//        }
//    }

    @Test
    public void calculatesShelfPriceWithSizeInOunces() {
        Beer beer = new Beer("20ounces", "shiner");
        assertEquals(21.50, beer.calculateShelfPrice());
    }

    @Test
    public void calculatesShelfPriceWithSizeIn_Ounces() {
        Beer beer = new Beer("20 ounces", "shiner");
        assertEquals(21.50, beer.calculateShelfPrice());
    }

    @Test
    public void calculatesShelfPriceWithSizeInOz() {
        Beer beer = new Beer("20oz", "shiner");
        assertEquals(21.50, beer.calculateShelfPrice());
    }

    @Test
    public void calculatesShelfPriceWithSizeIn_Oz() {
        Beer beer = new Beer("20 oz", "shiner");
        assertEquals(21.50, beer.calculateShelfPrice());
    }

    @Test
    public void calculatesShelfPriceAndAssumesSizeInOunces() {
        Beer beer = new Beer("20", "shiner");
        assertEquals(21.50, beer.calculateShelfPrice());
    }

    @Test
    public void calculatesShelfPriceWithSizeAsLarge() {
        Beer beer = new Beer("Large", "shiner");
        assertEquals(17.50, beer.calculateShelfPrice());
    }

    @Test
    public void calculatesShelfPriceWithDecimalOunces() {
        Beer beer = new Beer("2.5", "shiner");
        assertEquals(4.0, beer.calculateShelfPrice());
    }

}
