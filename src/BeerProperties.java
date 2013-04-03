import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class BeerProperties {

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

    public String getConfigPropertyName(String specialConfig) {
        return (specialConfig == "") ? "config.properties" : specialConfig;
    }

    private double findPropertyFromConfig(Properties prop, String beerName, String modifier) { // Can mock this now
        return Double.parseDouble(prop.getProperty(modifier + "." + beerName, "0"));  //will split on dot
    }
}
