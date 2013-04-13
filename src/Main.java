public class Main {

    public static void main(String[] args) {
        BeerList beerList = new BeerList(new BeerPropertiesGetter("beer.properties").getBeerProperties());
        System.out.println(beerList.getBeerMenu());
    }

}
