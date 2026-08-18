import java.sql.SQLException;

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
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid price!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}