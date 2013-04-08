import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class BeerProperties {

    private Properties properties;
    private ArrayList<String> beerNames;

    public BeerProperties(String configLocation) {
        this.properties = introduceConfig(configLocation);
        this.beerNames = extractBeerNames(properties);
    }

    private ArrayList<String> extractBeerNames(Properties properties) {
        ArrayList<String> beerNames = new ArrayList<String>();
        for(String propertyName : properties.stringPropertyNames()){
            String[] partsOfPropertyName = propertyName.split("\\.");
            for(String namePart :partsOfPropertyName){
                if(namePart.contains("base")){
                    beerNames.add((String) beerNameOf(propertyName));
                }
            }

        }
//        while(properties.propertyNames().hasMoreElements()){
//            beerNames.add((String) properties.propertyNames().nextElement());
//
//        }

        return beerNames;
    }

    private String beerNameOf(String propertyName) {
        return propertyName.replace("base.", "");
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
