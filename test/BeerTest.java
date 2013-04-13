import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class BeerTest {

    @Test
    public void sizeOfPintIs16() {
        assertEquals(16.0, BeerSize.PINT.ounces("doesn't matter"));
    }

    @Test
    public void beerOuncesKnowsItsName() {
        assertEquals(BeerSize.IMPERIAL_PINT, BeerSize.getFromName("imperial pint"));
    }

    @Test
    public void getsBeerBasePrice() {
        Beer beer = new Beer("bottle", "shiner");
        assertEquals(1.50, beer.getBasePrice());
    }

    @Test
    public void getsBeerShelfPriceWhenInABottle() {
        Beer beer = new Beer("bottle", "budweiser");
        assertEquals(7.2, beer.calculateShelfPrice(beer.getSize(), beer.getBasePrice(),
                beer.getVolume(), beer.getPricePerUnitVolume(), beer.getProp()));
    }

    @Test
    public void getsBeerShelfPriceWhenFromAKeg() {
        Beer beer = new Beer("8", "budweiser");
        assertEquals(3.8, beer.calculateShelfPrice(beer.getSize(), beer.getBasePrice(),
                beer.getVolume(), beer.getPricePerUnitVolume(), beer.getProp()));

    }

    @Test
    public void throwsExeptionWhenGivenNonsenseSize() {
        Beer beer = new Beer("happy", "shiner");
        try {
            beer.calculateShelfPrice(beer.getSize(), beer.getBasePrice(),
                    beer.getVolume(), beer.getPricePerUnitVolume(), beer.getProp());
        } catch (NumberFormatException e) {
        }
    }

    @Test
    public void calculatesShelfPriceWithSizeInOunces() {
        Beer beer = new Beer("20ounces", "shiner");
        assertEquals(11.50, beer.calculateShelfPrice(beer.getSize(), beer.getBasePrice(),
                beer.getVolume(), beer.getPricePerUnitVolume(), beer.getProp()));
    }

    @Test
    public void calculatesShelfPriceWithSizeIn_Ounces() {
        Beer beer = new Beer("20 ounces", "shiner");
        assertEquals(11.50, beer.calculateShelfPrice(beer.getSize(), beer.getBasePrice(),
                beer.getVolume(), beer.getPricePerUnitVolume(), beer.getProp()));
    }

    @Test
    public void calculatesShelfPriceWithSizeInOz() {
        Beer beer = new Beer("20oz", "shiner");
        assertEquals(11.50, beer.calculateShelfPrice(beer.getSize(), beer.getBasePrice(),
                beer.getVolume(), beer.getPricePerUnitVolume(), beer.getProp()));
    }

    @Test
    public void calculatesShelfPriceWithSizeIn_Oz() {
        Beer beer = new Beer("20 oz", "shiner");
        assertEquals(11.50, beer.calculateShelfPrice(beer.getSize(), beer.getBasePrice(),
                beer.getVolume(), beer.getPricePerUnitVolume(), beer.getProp()));
    }

    @Test
    public void calculatesShelfPriceAndAssumesSizeInOunces() {
        Beer beer = new Beer("20", "shiner");
        assertEquals(11.50, beer.calculateShelfPrice(beer.getSize(), beer.getBasePrice(),
                beer.getVolume(), beer.getPricePerUnitVolume(), beer.getProp()));
    }

    @Test
    public void calculatesShelfPriceWithSizeAsLarge() {
        Beer beer = new Beer("Large", "shiner");
        assertEquals(9.5, beer.calculateShelfPrice(beer.getSize(), beer.getBasePrice(),
                beer.getVolume(), beer.getPricePerUnitVolume(), beer.getProp()));
    }

    @Test
    public void calculatesShelfPriceWithDecimalOunces() {
        Beer beer = new Beer("2.5", "shiner");
        assertEquals(2.75, beer.calculateShelfPrice(beer.getSize(), beer.getBasePrice(),
                beer.getVolume(), beer.getPricePerUnitVolume(), beer.getProp()));
    }
}
