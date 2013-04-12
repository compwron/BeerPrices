import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class BeerListTest {
    @Test
    public void getsEmptyBeerMenuWhenThereIsNoBeerConfig() {
        BeerList beerList = new BeerList(mock(BeerProperties.class));
        assertEquals("", beerList.getBeerMenu());
    }

//    @Test
//    public void beerMenuShouldOneBeerWhenThatBeerIsInProperties(){
//        BeerProperties prop = mock(BeerProperties.class);
//        String beerName = "Shiner";
//        ArrayList<String> beerNames = new ArrayList<String>();
//        beerNames.add(beerName);
//        when(prop.getProperties()).thenReturn(prop);
//
//        BeerList beerList = new BeerList(prop);
//
//        String beerMenu = beerList.getBeerMenu();
//        assertTrue(beerMenu.contains(beerName));
//    }


}
