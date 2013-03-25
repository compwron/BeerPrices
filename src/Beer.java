import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.io.FileInputStream;

public class Beer {
    String volume;
    Double price;
    String beerName;
    Double basePrice;

    public Beer(String volume, String beerName) {
        introduceConfig();

        this.basePrice = findBasePrice(beerName);
    }

    protected Double findBasePrice(String beerName) {
        return 0.99;
    }

    public Double getBasePrice(){
        return basePrice;
    }

    private void introduceConfig() {
        Properties basePriceProperties = new Properties();

        try {
            basePriceProperties.load(new FileInputStream("config.properties"));
            System.out.println(basePriceProperties.getProperty("shiner"));
        }
        catch (FileNotFoundException e) {
            System.err.println("FileNotFoundException: " + e.getMessage());
        }
        catch (IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
        }
    }
}
