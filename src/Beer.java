import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.io.FileInputStream;

public class Beer {
    String volume;
    String price;
    String beerName;
    String basePrice;
    Properties prop;

    public Beer(String vol, String name) {
        this.volume = vol;
        this.beerName = name;
        introduceConfig();
        this.basePrice = prop.getProperty(beerName, "nope");
    }

    public String getBasePrice(){
        return basePrice;
    }

    public void introduceConfig() {
        Properties basePriceProperties = new Properties();

        try {
            basePriceProperties.load(new FileInputStream("config.properties"));
        }
        catch (FileNotFoundException e) {
            System.err.println("FileNotFoundException: " + e.getMessage());
        }
        catch (IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
        }
        this.prop = basePriceProperties;
    }

    protected String getsAllProperties(){
        String allProperties = new String("");
        for(String key : prop.stringPropertyNames()) {
            String value = prop.getProperty(key);
            allProperties += (key + " => " + value + "\n");
        }
        return allProperties;
    }

    protected void printsAllProperties(){
        prop.list(System.out);
    }

}
