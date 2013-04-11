import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class BeerProperties {

    private Properties properties;
    private ArrayList<String> beerNames;

    public BeerProperties(String configLocation) {
        this.properties = loadConfig(configLocation);
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

    public Properties loadConfig(String configName) {
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

    public ArrayList<String> getBeerNames() {
        return beerNames;
    }
}
