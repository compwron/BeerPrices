import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.io.FileInputStream;

public class Beer {
    String size;
    double shelfPrice;
    String beerName;
    double basePrice;
    Properties prop;
    double volume;
    double pricePerUnitVolume;

    public Beer(String volume, String name) {
        this.size = volume.toLowerCase();
        this.beerName = name.toLowerCase();
        introduceConfig();
        this.basePrice = Double.parseDouble(prop.getProperty(beerName, "nope"));
        this.shelfPrice = calculateShelfPrice();
    }

    public double getBasePrice(){
        return basePrice;
    }

    public void introduceConfig() {
        Properties basePriceProperties = new Properties();

        try {
            basePriceProperties.load(new FileInputStream("config.properties"));
        }
        catch (FileNotFoundException e) {
            System.err.println("FileNotFoundException: " + e.getMessage());
        }
        catch (IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
        }
        this.prop = basePriceProperties;
    }

    public double calculateShelfPrice() {
        shelfPrice = basePrice;
        if (beerIsInABottle()) {
            shelfPrice += Double.parseDouble(prop.getProperty("bottleCharge"));
        } else {
            this.volume = calcVolume();
            this.pricePerUnitVolume = findPricePerUnitVolume();
            shelfPrice += volume / pricePerUnitVolume;
        }
        return shelfPrice;
    }

    private double findPricePerUnitVolume() {
        return 1;
    }

    private double calcVolume() {
        return 1;
    }

    private boolean beerIsInABottle() {
        return (size == "bottle") ? true : false;
    }

}
