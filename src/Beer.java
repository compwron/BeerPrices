import com.sun.corba.se.impl.logging.ORBUtilSystemException;

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
        if (size.equals("large")) { return 16; }
        else if (size.equals("medium")) { return 12; }
        else if (size.equals("small")) { return 8; }
        else { return parseSize(); }
    }

    private double parseSize() {
        try { return Double.parseDouble(size); }
        catch (NumberFormatException  e) {
            Pattern patNumAlone = Pattern.compile("(\\d*)");
            Matcher matNumAlone = patNumAlone.matcher(size);
            if (matNumAlone.matches()) { return Double.parseDouble(matNumAlone.group(1)); }

            Pattern patOz = Pattern.compile("(\\d*).*oz");
            Matcher matOz = patOz.matcher(size);
            if (matOz.matches()) { return Double.parseDouble(matOz.group(1)); }

            Pattern patOunces = Pattern.compile("(\\d*).*ounces");
            Matcher matOunces = patOunces.matcher(size);
            if (matOunces.matches()) { return Double.parseDouble(matOunces.group(1)); }
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
