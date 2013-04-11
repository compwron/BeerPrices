import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");

//        BeerList beerList = (BeerList) context.getBean("beerList");

        BeerList beerList = new BeerList(new PropertyGetter("beer.properties").getBeerProperties()); // getBeerProperties returns ArrayList<BeerProperty>
        System.out.println(beerList.getBeerMenu());

    }

}
