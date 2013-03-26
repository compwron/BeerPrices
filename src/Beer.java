import com.sun.corba.se.impl.logging.ORBUtilSystemException;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Properties;
import java.io.FileInputStream;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Math.round;

public class Beer {
    String size;
    double shelfPrice;
    String beerName;
    double basePrice;
    double volume;
    double pricePerUnitVolume;
    Properties prop;

    public Beer(String volume, String name) {
        this.size = volume.toLowerCase();
        this.beerName = name.toLowerCase();
        introduceConfig();
        this.basePrice = Double.parseDouble(prop.getProperty("base." + beerName, "0"));
        if (basePrice == 0) {
            System.out.println("Please update the beer properties file and add this new beer!");
        }
        this.shelfPrice = calculateShelfPrice();
    }

    public void introduceConfig() {
        Properties basePriceProperties = new Properties();
        try { basePriceProperties.load(new FileInputStream(getConfigPropertyName())); }
        catch (FileNotFoundException e) {
            System.err.println("FileNotFoundException: " + e.getMessage());
        }
        catch (IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
        }
        this.prop = basePriceProperties;
    }

    public String getConfigPropertyName() {
        return "config.properties";
    }

    public double getBasePrice(){
        return basePrice;
    }

    public String formatShelfPrice(){
        NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);
        return n.format(shelfPrice);
    }

    public double calculateShelfPrice() {
        shelfPrice = basePrice;
        if (beerIsInABottle()) {
            shelfPrice += Double.parseDouble(prop.getProperty("bottleCharge"));
        }
        this.volume = calcVolume();
        this.pricePerUnitVolume = Double.parseDouble(prop.getProperty("ounce." + beerName, "0"));
        shelfPrice += volume * pricePerUnitVolume;

        return roundTwoDecimals(shelfPrice);
    }

    double roundTwoDecimals(double d) {
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.valueOf(twoDForm.format(d));
    }

    private double calcVolume() {
        if (size.equals("large")) { return 16; }
        else if (size.equals("pint")) {return 16; }
        else if (size.equals("imperial pint")) {return 20; }
        else if (size.equals("bottle")) { return 12; }
        else if (size.equals("medium")) { return 12; }
        else if (size.equals("small")) { return 8; }
        else if (size.equals("flight")) {return 3; }
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
            else { return 0; }
        }
    }

    private boolean beerIsInABottle() {
        return (size == "bottle") ? true : false;
    }

}
