import java.sql.*;
import java.util.List;

import javax.swing.JOptionPane;

public class InterfaceLogic {
    private static ProductDAO dao = new ProductDAO();

    public void insert() {
        String name = JOptionPane.showInputDialog("Name: ");
        if (name == null || name.isBlank()) return;

        String price = JOptionPane.showInputDialog("Price: ");
        if (price == null || price.isBlank()) return;

        try {
            double normalizedPrice = Double.parseDouble(price.replace(',', '.'));
            dao.insert(new Product(name, normalizedPrice));
            JOptionPane.showMessageDialog(null, "Product inserted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid price!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void list() {
        try {
            List<Product> products = dao.list();
    
            if (products.isEmpty()) {
                JOptionPane.showMessageDialog(null, "There is no products registred!", "List", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
    
            StringBuilder sb = new StringBuilder("Registred products: \n\n");
            for (Product p : products) {
                sb.append(p.getId()).append(" - ")
                .append(p.getName()).append(" - R$")
                .append(p.getPrice()).append('\n');
            }
    
            JOptionPane.showMessageDialog(null, sb.toString(), "Product List", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Database Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}