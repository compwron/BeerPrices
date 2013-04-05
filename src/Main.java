import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");

        BeerList beerList = (BeerList) context.getBean("beerList");
        System.out.println(beerList.getBeerMenu());

    }

}
