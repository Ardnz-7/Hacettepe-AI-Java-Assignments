import java.util.Date;

public class Product {

    String name;
    String membershipType;
    Date startDate;
    Date endDate;
    double price;

    Product(String name, String membershipType, Date startDate, Date endDate, double price) {
        this.name = name;
        this.membershipType = membershipType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
    }
}
