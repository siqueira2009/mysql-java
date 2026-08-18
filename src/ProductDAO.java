import java.sql.*;
import java.util.*;

public class ProductDAO {

    @SuppressWarnings("CallToPrintStackTrace")
    public void insert(Product p) throws SQLException {
        String sql = "INSERT INTO produtos (nome, preco) VALUES (?, ?)";

        try (Connection conn = DbConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getName());
            stmt.setDouble(2, p.getPrice());
            stmt.executeUpdate();
            System.out.println("Product inserted successfully.");
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public List<Product> list() throws SQLException {
        String sql = "SELECT * FROM produtos";

        try (Connection conn = DbConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            List<Product> lista = new ArrayList<>();
            while (rs.next()) {
                Product p = new Product(rs.getString("nome"), rs.getDouble("preco"));
                p.setId(rs.getInt("id"));
                lista.add(p);
            }
            return lista;
        } catch (SQLException e) {
            return null;
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public void update(Product p) throws SQLException {
        String sql = "UPDATE produtos SET nome=?, preco=? WHERE id=?";

        try (Connection conn = DbConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getName());
            stmt.setDouble(2, p.getPrice());
            stmt.setInt(3, p.getId());
            stmt.executeUpdate();
            System.out.println("Product updated successfully.");
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM produtos WHERE id=?";

        try (Connection conn = DbConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Product deleted successfully.");
        }
    }
}