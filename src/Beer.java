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
        this.size = size.toLowerCase(); // "this." is only necessary if names are the same
        this.beerName = name.toLowerCase();
        introduceConfig();
        this.basePrice = Double.parseDouble(prop.getProperty("base." + beerName, "0")); // if I make this a new method then I can mock property and test it
        if (basePrice == 0) {
            System.out.println("Please update the beer properties file and add this new beer (" + size + ")!" );
        }
        this.shelfPrice = calculateShelfPrice();
    }

    public void introduceConfig() { //have this return a properties rather than being void.  So you can test it.
        Properties basePriceProperties = new Properties();
        try {
            basePriceProperties.load(new FileInputStream(getConfigPropertyName()));
        } catch (FileNotFoundException e) {
            System.err.println("FileNotFoundException: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
        }
        this.prop = basePriceProperties;
    }

    public String getConfigPropertyName() {
        return "config.properties";
    }

    public double getBasePrice() {
        return basePrice;
    }

    public String formatShelfPrice() {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.US);
        return numberFormat.format(shelfPrice);
    }

    public double calculateShelfPrice() { //change the class variables to passed variables
        // do this so that 1) testing is better, 2) you prevent mistakes in the future, 3) you make the
        //next person's job easier, 4) you don't worry about random other methods changing your precious class variables
        shelfPrice = basePrice;
        if (beerIsInABottle()) {
            shelfPrice += Double.parseDouble(prop.getProperty("bottleCharge"));
        }
        this.volume = calcVolume();
        this.pricePerUnitVolume = Double.parseDouble(prop.getProperty("ounce." + beerName, "0"));
        shelfPrice += volume * pricePerUnitVolume;

        return roundTwoDecimals(shelfPrice);
    }

    double roundTwoDecimals(double price) { // use real names to help whoever comes next.  Or your later self.
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.valueOf(twoDForm.format(price));
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
    };

    private double calcVolume() {
        return BeerOunces.getFromName(size).ounces(size); //unfortunate duplication here...
    }

    private boolean beerIsInABottle() {
        return (size == "bottle") ? true : false;
    }

}
