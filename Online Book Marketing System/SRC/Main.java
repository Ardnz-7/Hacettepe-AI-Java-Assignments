import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedWriter out = new BufferedWriter(new FileWriter("output.txt"));
        String purchaseOrder = args[0];
        String priceCatalog = args[1];

        try {

            BufferedReader priceCatalogReader = new BufferedReader(new FileReader(priceCatalog));
            HashMap<String, Product> products = new HashMap<>();

            String line;
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            while ((line = priceCatalogReader.readLine()) != null) {
                String[] parts = line.split("\t");
                String name = parts[0];
                String membershipType = parts[1];
                Date startDate = dateFormat.parse(parts[2]);
                Date endDate = dateFormat.parse(parts[3]);
                double totalCost = Double.parseDouble(parts[4]);

                String key = name + "_" + membershipType + "_" + dateFormat.format(startDate);
                products.put(key, new Product(name, membershipType, startDate, endDate, totalCost));
            }

            priceCatalogReader.close();

            BufferedReader purchaseOrderReader = new BufferedReader(new FileReader(purchaseOrder));
            SimpleDateFormat dateOfBirthFormat = new SimpleDateFormat("dd.MM.yyyy");
            HashMap<String, Customer> customers = new HashMap<>();

            while ((line = purchaseOrderReader.readLine()) != null) {

                String[] parts = line.split("\t");
                String customerName = parts[0];
                String membershipType = parts [1];
                Date shoppingDate = dateOfBirthFormat.parse(parts[2]);
                String [] items = new String[]{parts[3], parts[5]};

                Customer customer = new Customer(customerName, membershipType, shoppingDate);
                int i=0;
                for (String item : items) {

                    int quantity = Integer.parseInt(parts[4+i]);

                    for (Map.Entry<String, Product> entry : products.entrySet()) {
                        String key = entry.getKey();
                        Product product = entry.getValue();

                        if (isDateWithinRange(shoppingDate, product.startDate, product.endDate) &&
                                item.equals(product.name) &&
                                membershipType.equals(product.membershipType)) {
                            customer.shoppingList.put(key, quantity);
                        }
                    }
                    i+=2;
                }
                customers.put(customerName, customer);
            }

            purchaseOrderReader.close();

            for (Customer customer : customers.values()) {
                out.write("------------Bill for Customer------------ " + "\n");
                out.write("Customer: " + customer.name  + "\n");
                out.write("Membership Type: " + customer.membershipType + "\n");
                out.write("Date: " + dateFormat.format(customer.shoppingDate) + "\n");
                out.write("\nItems Purchased:" + "\n");

                double totalCost = 0.0;

                for (String itemName : customer.shoppingList.keySet()) {
                    int quantity = customer.shoppingList.get(itemName);
                    Product product = products.get(itemName);
                    if (product != null && isDateWithinRange(customer.shoppingDate, product.startDate, product.endDate)
                            && customer.membershipType.equals(product.membershipType)) {

                        double itemCost = product.price * quantity;
                        totalCost += itemCost;
                        out.write(itemName.substring(0,6) + " (Qty: " + quantity + ") - " + product.price + " each" + "\n");
                        out.write("(Valid from " + dateFormat.format(product.startDate) + " to " + dateFormat.format(product.endDate) + ")" + "\n");
                        out.write("Subtotal: " + itemCost + "\n");
                        out.write("\n" + "\n");

                    }
                    out.write("Total cost: " + totalCost + "\n");
                    out.write("\n");

                }
            }} catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        out.close();
    }


    private static boolean isDateWithinRange(Date date, Date startDate, Date endDate){

        return date.after(startDate) && date.before(endDate);
    }

}