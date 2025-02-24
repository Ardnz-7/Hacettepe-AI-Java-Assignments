import java.util.Date;
import java.util.HashMap;

public class Customer {
    String name;
    String membershipType;
    Date shoppingDate;
    HashMap<String, Integer> shoppingList;

    Customer(String name, String membershipType, Date shoppingDate) {
        this.name = name;
        this.membershipType = membershipType;
        this.shoppingDate = shoppingDate;
        this.shoppingList = new HashMap<>();
    }
}
