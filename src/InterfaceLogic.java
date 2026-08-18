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
    
            if (products == null || products.isEmpty()) {
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

    public void delete() {
        try {
            String id = JOptionPane.showInputDialog("Product ID: ");
            if (id == null || id.isBlank()) return;

            List<Product> products = dao.list();
            
            if (products == null || products.isEmpty()) {
                JOptionPane.showMessageDialog(null, "There is no products registred!", "Delete", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            Product singleProduct = null;
            for (Product p : products) {
                if (String.valueOf(p.getId()).equals(id)) {
                    singleProduct = p;
                    break;
                }
            }

            if (singleProduct == null) {
                JOptionPane.showMessageDialog(null, "Product with ID " + id + " not found!", "Delete", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(null, "Do you want do delete item " + singleProduct.getName() + '?', "Delete item", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        
            if (confirm == JOptionPane.YES_OPTION) {
                dao.delete(singleProduct.getId());
                JOptionPane.showMessageDialog(null, "Product " + singleProduct.getName() + " delete sucessfully!", "Delete item", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Product " + singleProduct.getName() + " was NOT deleted.", "Delete item", JOptionPane.CANCEL_OPTION);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Database Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}