public class Main {

    public static void main(String[] args) {
        Beer bottleOfShiner = new Beer("bottle", "Shiner");
        System.out.println("The price of a bottle of Shiner is " + bottleOfShiner.formatShelfPrice());

        Beer pintOfStella = new Beer("pint", "Stella");
        System.out.println("The price of a pint of Stella is " + pintOfStella.formatShelfPrice());

        Beer largeBudweiser = new Beer("large", "Budweiser");
        System.out.println("The price of a large Budweiser is " + largeBudweiser.formatShelfPrice());

        Beer corona12oz = new Beer("12oz", "Corona");
        System.out.println("The price of 12oz of Corona is " + corona12oz.formatShelfPrice());

        Beer shinerInAFlight = new Beer("flight", "Shiner");
        System.out.println("The price of Shiner in a flight of beers is " + shinerInAFlight.formatShelfPrice());

    }

}
