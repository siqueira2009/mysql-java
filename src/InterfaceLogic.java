import java.sql.*; // Importa tudo que é necessário para uma conexão SQL

import javax.swing.*; // Importa tudo do Swing
import java.util.List; // Importa as listas em Java

public class InterfaceLogic {
    // Cria um dao que será usado em todos os métodos
    private static ProductDAO dao = new ProductDAO();

    // Método de inserir itens
    public void insert() {
        // Pega o nome do item usando o diálogo de input
        String name = JOptionPane.showInputDialog("Name: ");
        if (name == null || name.isBlank()) return;

        // Pega o preço do item usando o diálogo de input
        String price = JOptionPane.showInputDialog("Price: ");
        if (price == null || price.isBlank()) return;

        // Depois tenta...
        try {
            // Normalizar o preço (vírgula > ponto && double)
            double normalizedPrice = Double.parseDouble(price.replace(',', '.'));

            // Insere no banco de dados
            dao.insert(new Product(name, normalizedPrice));

            // Avisa se conseguiu inserir
            JOptionPane.showMessageDialog(null, "Product inserted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) { // Se houve erro no formato do número (preço)
            // Avisa isso
            JOptionPane.showMessageDialog(null, "Invalid price!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) { // Se o DAO retornar erro de SQL
            // Avisa isso
            JOptionPane.showMessageDialog(null, "Database Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método de listar itens
    public void list() {
        try { // Primeiro tenta...
            // Pegar todos os itens
            List<Product> products = dao.list();
    
            // Se não houver nenhum, avisa isso e retorna
            if (products == null || products.isEmpty()) {
                JOptionPane.showMessageDialog(null, "There is no products registred!", "List", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
    
            // Cria um StringBuilder para contatenar textos (mais eficiente)
            StringBuilder sb = new StringBuilder("Registred products: \n\n");
            for (Product p : products) {
                sb.append(p.getId()).append(" - ")
                .append(p.getName()).append(" - R$")
                .append(p.getPrice()).append('\n');
            }
    
            // Mostra os itens usando um diálogo de mensagem
            JOptionPane.showMessageDialog(null, sb.toString(), "Product List", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) { // Se o DAO retornar erro de SQL
            // Avisa isso
            JOptionPane.showMessageDialog(null, "Database Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método de atualizar itens
    public void update() {
        try { // Primeiro tenta...
            // Pegar o ID usando um diálogo de input
            String id = JOptionPane.showInputDialog("Product ID: ");
            if (id == null || id.isBlank()) return;
    
            // Pega todos os itens no DB
            List<Product> products = dao.list();
            
            // Avisa se não tiver itens 
            if (products == null || products.isEmpty()) {
                JOptionPane.showMessageDialog(null, "There is no products registred!", "Delete", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
    
            // Procura o item do ID digitado
            Product singleProduct = null;
            for (Product p : products) { // Faz isso percorrendo cada um
                // E comparando os IDs
                // Aqui usa-se .equals(), pois o == compara local na memória e não conteúdo interno
                if (String.valueOf(p.getId()).equals(id)) {
                    singleProduct = p;
                    break;
                }
            }

            // Se não houver item, avisa que não encontrou item com tal ID e retorna
            if (singleProduct == null) {
                JOptionPane.showMessageDialog(null, "Product with ID " + id + " not found!", "Delete", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Depois pede o novo nome do item
            String newName = JOptionPane.showInputDialog(null, "Enter the new product name: ", singleProduct.getName());
            if (newName == null || newName.isBlank()) {
                newName = singleProduct.getName();
            }

            // E também pede o novo preço do item
            String newPrice = JOptionPane.showInputDialog(null, "Enter the new product price:", singleProduct.getPrice());
            if (newPrice == null || newPrice.isBlank()) {
                newPrice = String.valueOf(singleProduct.getPrice());
            }
            double newPriceDouble = Double.parseDouble(newPrice.replace(',', '.'));

            // Depois instancia um novo produto com os novos dados
            Product newProduct = new Product(newName, newPriceDouble);
            newProduct.setId(singleProduct.getId());

            // E atualiza usando o DAO
            dao.update(newProduct);
        } catch (SQLException e) { // Se o DAO retornar erro de SQL
            // Avisa isso
            JOptionPane.showMessageDialog(null, "Database Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método de deletar itens
    public void delete() {
        try { // Primeiro tenta...
            // Pegar o ID usando um diálogo de input
            String id = JOptionPane.showInputDialog("Product ID: ");
            if (id == null || id.isBlank()) return;

            // Pega todos os produtos no DB
            List<Product> products = dao.list();
            
            // Se não houver produtos, avisa isso e retorna
            if (products == null || products.isEmpty()) {
                JOptionPane.showMessageDialog(null, "There is no products registred!", "Delete", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // Tenta achar o item específico, percorrendo o array
            Product singleProduct = null;
            for (Product p : products) {
                if (String.valueOf(p.getId()).equals(id)) {
                    singleProduct = p;
                    break;
                }
            }

            // Se não houver item, avisa que não encontrou item com tal ID e retorna
            if (singleProduct == null) {
                JOptionPane.showMessageDialog(null, "Product with ID " + id + " not found!", "Delete", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Cria um input de confirmação e guarda o valor
            int confirm = JOptionPane.showConfirmDialog(null, "Do you want do delete item " + singleProduct.getName() + '?', "Delete item", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        
            // Se for SIM...
            if (confirm == JOptionPane.YES_OPTION) {
                // Deleta o item e avisa isso
                dao.delete(singleProduct.getId());
                JOptionPane.showMessageDialog(null, "Product " + singleProduct.getName() + " delete sucessfully!", "Delete item", JOptionPane.INFORMATION_MESSAGE);
            } else { // Se for qualquer outra opção...
                // Não deleta, só avisa isso.
                JOptionPane.showMessageDialog(null, "Product " + singleProduct.getName() + " was NOT deleted.", "Delete item", JOptionPane.CANCEL_OPTION);
            }
        } catch (SQLException e) { // Se o DAO retornar erro de SQL
            // Avisa isso
            JOptionPane.showMessageDialog(null, "Database Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}