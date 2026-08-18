import java.sql.*; // Importa tudo que é necessário para uma conexão SQL
import java.util.*; // Importa todas as utilitárias do Java

// Classe do DAO (com métodos que conversam com o banco)
public class ProductDAO {

    // Método para inserir itens no banco de dados
    public void insert(Product p) throws SQLException { // Joga o SQL Exception para frente
        // Cria a query SQL para inserir em produtos, nas colunas nome e preço, dois valores (não passados na query por segurança)
        String query = "INSERT INTO produtos (nome, preco) VALUES (?, ?)";

        // Faz um try with resources
        // Tudo que é passado nos parênteses do try é fechado automaticamente no final de tudo
        // Permite usar um try sem finally
        try (Connection conn = DbConnection.connect(); PreparedStatement stmt = conn.prepareStatement(query)) {
            // Seta os valores variáveis (?) da query
            stmt.setString(1, p.getName());
            stmt.setDouble(2, p.getPrice());

            stmt.executeUpdate(); // Roda a query (sem retornar dados)

            // Dá um feedback no console
            System.out.println("Product inserted successfully.");
        }
    }

    // Método para listar itens no banco de dados
    public List<Product> list() throws SQLException {
        // Cria a query SQL que pega tudo de produtos
        String query = "SELECT * FROM produtos";

        // Faz um try with resources
        try (Connection conn = DbConnection.connect(); PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            // Cria uma lista que vai guardar os itens
            List<Product> list = new ArrayList<>();
            
            // Enquanto ainda tiver resultados
            while (rs.next()) {
                // Instancia produtos com os valores
                Product p = new Product(rs.getString("nome"), rs.getDouble("preco"));
                p.setId(rs.getInt("id"));

                // E adiciona na lista
                list.add(p);
            }

            // Depois retorna essa lista
            return list;
        } catch (SQLException e) { // Caso dê erro, retorna null por segurança
            return null;
        }
    }

    // Método para atualizar itens no banco de dados
    public void update(Product p) throws SQLException {
        // Query para atualizar itens no banco de dados quando o ID corresponder
        String query = "UPDATE produtos SET nome=?, preco=? WHERE id=?";

        try (Connection conn = DbConnection.connect(); PreparedStatement stmt = conn.prepareStatement(query)) {
            // Seta os valores variáveis da query
            stmt.setString(1, p.getName());
            stmt.setDouble(2, p.getPrice());
            stmt.setInt(3, p.getId());

            stmt.executeUpdate(); // Executa a query sem retornar nada

            // Dá um feedback no console
            System.out.println("Product updated successfully.");
        }
    }

    // Método de deletar itens no banco de dados
    public void delete(int id) throws SQLException {
        // Query para deletar um item que corresponde a certo ID
        String query = "DELETE FROM produtos WHERE id=?";

        // Usa um try with resources
        try (Connection conn = DbConnection.connect(); PreparedStatement stmt = conn.prepareStatement(query)) {
            // Seta os valores variáveis da query
            stmt.setInt(1, id);

            stmt.executeUpdate(); // Executa a query sem retornar nada

            // Dá um feedback no console
            System.out.println("Product deleted successfully.");
        }
    }
}