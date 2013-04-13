import java.util.ArrayList;
import java.util.Properties;

public class BeerProperties {

    private final ArrayList<BeerProperty> beerProperties;
    private ArrayList<String> beerNames;

    public BeerProperties(Properties properties) {
        this.beerNames = extractBeerNames(properties);
        this.beerProperties = extractBeerProperties(properties);
    }

    private ArrayList<BeerProperty> extractBeerProperties(Properties properties) {
        ArrayList<BeerProperty> allBeerProperties = new ArrayList<BeerProperty>();
        for (String propertyName : properties.stringPropertyNames()) {
            if (containsNewBeerName(propertyName, allBeerProperties)) {
                allBeerProperties.add(new BeerProperty(beerNameOf(propertyName)));
            }

        }
        return allBeerProperties;
    }

    private boolean containsNewBeerName(String propertyName, ArrayList<BeerProperty> currentBeerProperties) {
        if (!isBeerSpecificProperty(propertyName)) {
            return false;
        }
        for (String beerName : getBeerNames()) {
            if (propertyName.contains(beerName) && !beerIsIn(beerName, currentBeerProperties)) {
                return true;
            }
        }
        return false;
    }

    private boolean isBeerSpecificProperty(String propertyName) {
        for (String beerName : getBeerNames()) {
            if (propertyName.contains(beerName)) {
                return true;
            }
        }
        return false;
    }

    private boolean beerIsIn(String beerName, ArrayList<BeerProperty> currentBeerProperties) {
        for (BeerProperty beerProperty : currentBeerProperties) {
            if (beerProperty.beerName.equals(beerName)) {
                return true;
            }
        }
        return false;
    }

    private ArrayList<String> extractBeerNames(Properties properties) {
        ArrayList<String> beerNames = new ArrayList<String>();
        for (String propertyName : properties.stringPropertyNames()) {
            String[] partsOfPropertyName = propertyName.split("\\.");
            for (String namePart : partsOfPropertyName) {
                if (isBeerBasePrice(namePart)) {
                    beerNames.add(beerNameOf(propertyName));
                }
            }

        }
        return beerNames;
    }

    private boolean isBeerBasePrice(String namePart) {
        return namePart.contains("base");
    }

    private String beerNameOf(String propertyName) {
        String propertyNameWithoutBasePrefix = propertyName.replace("base.", "");
        String propertyNameWithoutOuncePrefix = propertyNameWithoutBasePrefix.replace("ounce.", "");
        return propertyNameWithoutOuncePrefix;
    }

    public ArrayList<String> getBeerNames() {
        return beerNames;
    }

    public ArrayList<BeerProperty> allBeerProperties() {
        return beerProperties;
    }
}
