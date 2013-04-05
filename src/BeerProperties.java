import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class BeerProperties {

    private static Properties properties;
    private ArrayList<String> beerNames;

    public BeerProperties(String configLocation) {
        this.properties = introduceConfig(configLocation);
    }

    public static Properties getProperties() {
        return properties;
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

    public String getConfigPropertyName(String specialConfig) {
        return (specialConfig == "") ? "config.properties" : specialConfig;
    }

    private double findPropertyFromConfig(Properties prop, String beerName, String modifier) { // Can mock this now
        return Double.parseDouble(prop.getProperty(modifier + "." + beerName, "0"));  //will split on dot
    }

    public ArrayList<String> getBeerNames() {
        return beerNames;
    }
}
