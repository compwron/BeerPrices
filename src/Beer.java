import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        prop = introduceConfig(getConfigPropertyName());
        basePrice = findPropertyFromConfig(prop, beerName, "base");
        if (basePrice == 0) {
            System.out.println("Please update the beer properties file and add this new beer (" + size + ")!" );
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

    public String getConfigPropertyName() { // Add optional variable passed in to
    // practice other config files, like Winter vs Summer
        return "config.properties";
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
        return BeerOunces.getFromName(size).ounces(size);
    }

    private boolean beerIsInABottle(String size) {
        return (size == "bottle") ? true : false;
    }

    public enum BeerOunces {
        PINT(){
            public double ounces(String size){
                return 16;
            }
            public String sizeName() {
                return "pint";
            }
        }, LARGE(){
            public double ounces(String size){
                return 16;
            }
            public String sizeName() {
                return "large";
            }
        }, IMPERIAL_PINT() {
            public double ounces(String size) {
                return 20;
            }
            public String sizeName() {
                return "imperial pint";
            }
        }, BOTTLE() {
            public double ounces(String size) {
                return 12;
            }
            public String sizeName() {
                return "bottle";
            }
        }, MEDIUM() {
            public double ounces(String size) {
                return 12;
            }
            public String sizeName() {
                return "medium";
            }
        }, SMALL() {
            public double ounces(String size) {
                return 8;
            }
            public String sizeName() {
                return "small";
            }
        }, FLIGHT() {
            public double ounces(String size) {
                return 3;
            }
            public String sizeName() {
                return "flight";
            }
        }, NONE() {
            public double ounces(String size) { //look into replacing with yaml because it has a parsing library for this sort of thing
                try { //it's a design flaw to take different types of input in the same design parameter.  So it gets > complicated than it needs to be
                    return Double.parseDouble(size);
                } catch (NumberFormatException e) { // put each into different methods so you can test it better!
                    Pattern patNumAlone = Pattern.compile("(\\d*)");
                    Matcher matNumAlone = patNumAlone.matcher(size);
                    if (matNumAlone.matches()) {
                        return Double.parseDouble(matNumAlone.group(1));
                    }

                    Pattern patOz = Pattern.compile("(\\d*).*oz");
                    Matcher matOz = patOz.matcher(size);
                    if (matOz.matches()) {
                        return Double.parseDouble(matOz.group(1));
                    }

                    Pattern patOunces = Pattern.compile("(\\d*).*ounces");
                    Matcher matOunces = patOunces.matcher(size);
                    if (matOunces.matches()) {
                        return Double.parseDouble(matOunces.group(1));
                    } else {
                        return 0;
                    }
                }
            }
            public String sizeName() {
                return "none";
            }
        };
        public abstract double ounces(String size);

        public static BeerOunces getFromName(String beerSize) {
            for (BeerOunces beerOunce : BeerOunces.values()) {
                if (beerOunce.sizeName().equals(beerSize)){
                    return beerOunce;
                }
            }
            return BeerOunces.NONE;
        }
        protected abstract String sizeName();
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
