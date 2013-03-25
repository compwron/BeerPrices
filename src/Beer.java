public class Beer {
    String volume;
    Double price;
    String beerName;
    Double basePrice;

    public Beer(String volume, String beerName) {
        this.basePrice = findBasePrice(beerName);
    }

    protected Double findBasePrice(String beerName) {
        return 0.99;
    }

    public Double getBasePrice(){
        return basePrice;
    }
}
