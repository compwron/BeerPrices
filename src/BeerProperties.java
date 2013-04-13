import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

public class BeerProperties {

    private final ArrayList<BeerProperty> beerProperties;
    private ArrayList<String> beerNames;

    public BeerProperties(Properties properties) {
        this.beerNames = extractBeerNames(properties);
        this.beerProperties = extractBeerProperties(properties);
    }

    private ArrayList<BeerProperty> extractBeerProperties(Properties properties) {
        HashMap<String, ArrayList<String>> beerDatums = beersWithDatums(properties);
        return beerPropertiesFromDatums(beerDatums);
    }

    private ArrayList<BeerProperty> beerPropertiesFromDatums(HashMap<String, ArrayList<String>> beerDatums) {
        ArrayList<BeerProperty> allBeerProperties = new ArrayList<BeerProperty>();
        for (String beerName : beerDatums.keySet()) {
            allBeerProperties.add(new BeerProperty(beerDatums.get(beerName)));
        }
        return allBeerProperties;
    }

    private HashMap<String, ArrayList<String>> beersWithDatums(Properties properties) {
        HashMap<String, ArrayList<String>> beerDatums = new HashMap<String, ArrayList<String>>();
        for (String propertyName : properties.stringPropertyNames()) {
            beerDatums.get(beerNameOf(propertyName)).add(propertyName);

        }
        return beerDatums;
    }

    private boolean isBeerSpecificProperty(String propertyName) {
        for (String beerName : getBeerNames()) {
            if (propertyName.contains(beerName)) {
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
