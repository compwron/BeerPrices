import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum BeerSize {
    PINT() {
        public double ounces(String size) {
            return 16;
        }

        public String sizeName() {
            return "pint";
        }
    }, LARGE() {
        public double ounces(String size) {
            return 16;
        }

        public String sizeName() {
            return "large";
        }
    }, IMPERIAL_PINT() {
        public double ounces(String size) {
            return 20;
        }

        public String sizeName() {
            return "imperial pint";
        }
    }, BOTTLE() {
        public double ounces(String size) {
            return 12;
        }

        public String sizeName() {
            return "bottle";
        }
    }, MEDIUM() {
        public double ounces(String size) {
            return 12;
        }

        public String sizeName() {
            return "medium";
        }
    }, SMALL() {
        public double ounces(String size) {
            return 8;
        }

        public String sizeName() {
            return "small";
        }
    }, FLIGHT() {
        public double ounces(String size) {
            return 3;
        }

        public String sizeName() {
            return "flight";
        }
    }, NONE() {
        public double ounces(String size) { //look into replacing with yaml because it has a parsing library for this sort of thing
            try { //it's a design flaw to take different types of input in the same design parameter.  So it gets > complicated than it needs to be
                return Double.parseDouble(size);
            } catch (NumberFormatException e) { // put each into different methods so you can test it better!
                Pattern patNumAlone = Pattern.compile("(\\d*)");
                Matcher matNumAlone = patNumAlone.matcher(size);
                if (matNumAlone.matches()) {
                    return Double.parseDouble(matNumAlone.group(1));
                }

                Pattern patOz = Pattern.compile("(\\d*).*oz");
                Matcher matOz = patOz.matcher(size);
                if (matOz.matches()) {
                    return Double.parseDouble(matOz.group(1));
                }

                Pattern patOunces = Pattern.compile("(\\d*).*ounces");
                Matcher matOunces = patOunces.matcher(size);
                if (matOunces.matches()) {
                    return Double.parseDouble(matOunces.group(1));
                } else {
                    return 0;
                }
            }
        }

        public String sizeName() {
            return "none";
        }
    };

    public abstract double ounces(String size);

    public static BeerSize getFromName(String beerSize) {
        for (BeerSize beerOunce : BeerSize.values()) {
            if (beerOunce.sizeName().equals(beerSize)) {
                return beerOunce;
            }
        }
        return BeerSize.NONE;
    }

    protected abstract String sizeName();
}