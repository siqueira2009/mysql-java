import java.util.*;

public class Main {
    @SuppressWarnings("ConvertToTryWithResources")

    public static void main(String[] args) {
        ProductDAO dao = new ProductDAO();
        Scanner sc = new Scanner(System.in);
        sc.useLocale(Locale.US);

        System.out.println(
            "1 - Insert | 2 - List | 3 - Update | 4 - Delete"
        );

        System.out.print("Choose an option: ");
        int option = sc.nextInt();
        sc.nextLine();

        switch (option) {
            case 1:
                System.out.print("Name: ");
                String name = sc.nextLine();

                System.out.print("Price: ");
                String price = sc.nextLine();
                double normalizedPrice = Double.parseDouble(price.replace(',', '.'));

                Product p = new Product(name, normalizedPrice);
                dao.insert(p);
                break;
            case 2: 
                for (Product prod : dao.list()) {
                    System.out.println(
                        prod.getId() + " - " +
                        prod.getName() + " - R$ " +
                        prod.getPrice());
                }
                break;
            case 3: 
                System.out.print("Product ID to UPDATE: ");
                int id = sc.nextInt();

                sc.nextLine();

                System.out.print("New product name: ");
                name = sc.nextLine();

                System.out.print("New product price: ");
                price = sc.nextLine();
                normalizedPrice = Double.parseDouble(price.replace(',', '.'));
    
                p = new Product(name, normalizedPrice);
                p.setId(id);
                dao.update(p);
                break;
            case 4:
                System.out.print("Product ID to DELETE: ");
                id = sc.nextInt();
                dao.delete(id);
                break;
            default:
                System.out.println("Invalid option! Try another one.");
                break;
        }
        sc.close();
    }
}