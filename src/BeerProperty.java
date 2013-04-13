import java.util.ArrayList;

public class BeerProperty {
    public String beerName = null;
    public Double pricePerOunce = null;
    public  Double basePrice = null;

    public BeerProperty(ArrayList<String> properties) {
        for(String property : properties){
            if(beerName == null){
                beerName = beerNameOf(property);
            }
            if(isBasePrice(property)){
                basePrice = basePriceOf(property);
            }
            if(isPricePerOunce(property)){
                pricePerOunce = pricePerOunceOf(property);
            }
        }
    }

    private Double pricePerOunceOf(String property) {
        return Double.parseDouble(property.replace("ounce."+ beerName + "=", ""));
    }

    private boolean isPricePerOunce(String property) {
        return property.contains("ounce");
    }

    private Double basePriceOf(String property) {
        return Double.parseDouble(property.replace("base."+beerName+"=", ""));
    }

    private boolean isBasePrice(String property) {
       return property.contains("base");
    }

    private String beerNameOf(String propertyName) {
        String[] propertyComponents = propertyName.split("=");
        String propertyNameWithoutBasePrefix = propertyComponents[0].replace("base.", "");
        String propertyNameWithoutOuncePrefix = propertyNameWithoutBasePrefix.replace("ounce.", "");
        return propertyNameWithoutOuncePrefix;
    }
}
