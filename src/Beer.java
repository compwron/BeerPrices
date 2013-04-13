import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Properties;

public class Beer {
    String size;
    double shelfPrice;
    String beerName;
    double basePrice;
    double volume;
    double pricePerUnitVolume;
    Properties prop;

    public Beer(String size, String name) {
        this.size = size.toLowerCase();
        beerName = name.toLowerCase();
        prop = introduceConfig(getConfigPropertyName(""));
        basePrice = findPropertyFromConfig(prop, beerName, "base");
        if (basePrice == 0) {
            System.out.println("Please update the beer properties file and add this new beer (" + size + ")!");
        }
        volume = calcVolume(this.size);
        pricePerUnitVolume = findPropertyFromConfig(prop, beerName, "ounce");
        this.shelfPrice = calculateShelfPrice(this.size, basePrice, volume, pricePerUnitVolume, prop);
    }

    private double findPropertyFromConfig(Properties prop, String beerName, String modifier) { // Can mock this now
        return Double.parseDouble(prop.getProperty(modifier + "." + beerName, "0"));
    }

    public Properties introduceConfig(String configName) {
        Properties basePriceProperties = new Properties();
        try {
            basePriceProperties.load(new FileInputStream(configName));
        } catch (FileNotFoundException e) {
            System.err.println("FileNotFoundException: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
        }
        return basePriceProperties;
    }

    public String getConfigPropertyName(String specialConfig) {
        return (specialConfig == "") ? "config.properties" : specialConfig;
    }

    public String formatShelfPrice(double shelfPrice) {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.US);
        return numberFormat.format(shelfPrice);
    }

    public double calculateShelfPrice(String size, double basePrice,
                                      double volume, double pricePerUnitVolume, Properties prop) {
        double price = basePrice;
        if (beerIsInABottle(size)) {
            price += Double.parseDouble(prop.getProperty("bottleCharge"));
        }

        price += volume * pricePerUnitVolume;

        return roundTwoDecimals(price);
    }

    double roundTwoDecimals(double price) {
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.valueOf(twoDForm.format(price));
    }

    private double calcVolume(String size) {
        return BeerSize.getFromName(size).ounces(size);
    }

    private boolean beerIsInABottle(String size) {
        return (size == "bottle") ? true : false;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public double getShelfPrice() {
        return shelfPrice;
    }

    public String getSize() {
        return size;
    }

    public double getVolume() {
        return volume;
    }

    public double getPricePerUnitVolume() {
        return pricePerUnitVolume;
    }

    public Properties getProp() {
        return prop;
    }
}
