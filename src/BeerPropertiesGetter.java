import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class BeerPropertiesGetter {
    private BeerProperties beerProperties;

    public BeerPropertiesGetter(String configFileLocation) {
        beerProperties = new BeerProperties(loadConfig(configFileLocation));
    }

    public BeerProperties getBeerProperties() {
        return beerProperties;
    }

    private Properties loadConfig(String configName) {
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
}
