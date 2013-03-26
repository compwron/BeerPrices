import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.io.FileInputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        this.basePrice = Double.parseDouble(prop.getProperty("base." + beerName, "nope"));
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
        double ounces = 0;
        if (size == "large") { ounces = 16; }
        else if (size == "medium") { ounces = 12; }
        else if (size == "small") { ounces = 8; }
        else { ounces = parseSize(); }
        return ounces;
    }

    private double parseSize() {
        try { return Double.parseDouble(size); }
        catch (NumberFormatException  e) {
            Pattern pattern = Pattern.compile("(\\d*).*ounces"); //(\\d*)||(\\d*).*oz||(\\d*).*ounces");
            Matcher matcher = pattern.matcher(size);
            if (matcher.matches()) {
                System.out.println("It matches, all right --> " + size + " --> " + matcher.group(1));
                return Double.parseDouble(matcher.group(1));
            }
            else {
                System.err.println("Caught NumberFormatException: " + e.getMessage());
                return 0;
            }
        }
    }

    private boolean beerIsInABottle() {
        return (size == "bottle") ? true : false;
    }

}
