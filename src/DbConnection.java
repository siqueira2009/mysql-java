import java.sql.*; // Importa tudo que é necessário para uma conexão SQL

public class DbConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/loja"; // URL do DB
    private static final String USER = "root"; // Usuário do DB
    private static final String PASSWORD = "ds123"; // Senha do DB 

    // ISSO TUDO ESTÁ HARDCODED, O QUE É ERRADO
    // TENHO CONHECIMENTO DISSO, MAS FIZ POR QUESTÕES PRÁTICAS DA ATIVIDADE

    // Método que conecta no banco de dados usando os dados
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}