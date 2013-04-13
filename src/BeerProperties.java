import java.util.ArrayList;
import java.util.Properties;

public class BeerProperties {

    private ArrayList<String> beerNames;

    public BeerProperties(Properties properties) {
        this.beerNames = extractBeerNames(properties);
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
        return propertyName.replace("base.", "");
    }

    public ArrayList<String> getBeerNames() {
        return beerNames;
    }
}
