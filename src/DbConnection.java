import java.sql.*; // Importa tudo que é necessário para uma conexão SQL

import javax.swing.*;
import java.awt.*;

public class DbConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/loja"; // URL do DB
    private static String USER = null; // Usuário do DB que será coletada depois
    private static String PASSWORD = null; // Senha do DB que será coletada depois
    private static InterfaceComponents ic = new InterfaceComponents(); // Instancia a classe de componentes de interface
    
    // Método que conecta no banco de dados usando os dados
    public static Connection connect() throws SQLException {
        if (USER == null || PASSWORD == null) { // Se não tiver nenhum usuário ou senha
            askCredentials(); // Pede esses dados
        }

        try { // Depois, tenta conectar ao banco de dados
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) { // Em caso de erro
            // Reseta as credenciais
            USER = null;
            PASSWORD = null;
            
            // Joga esse erro pra quem chamou o método (mostrando ele na interface)
            throw e;
        }
    }

    // Método que pede as credenciais para conectar ao banco
    public static void askCredentials() throws SQLException {
        JTextField userField = new JTextField("root"); // Cria um "input" para pedir o usuário (já vem sugerido o root)
        JPasswordField passwordField = new JPasswordField(); // Cria um "input" para pedir a senha (mascarada)
        JLabel userLabel = ic.createStyledText("Database user: ", "plain", "left"); // Cria um label para o usuário 
        JLabel passwordLabel = ic.createStyledText("Password user: ", "plain", "left"); // E um label para a senha

        // Cria um painel
        JPanel panel = new JPanel(new GridLayout(4, 1, 5, 5));

        // E abriga TUUUUDO que foi criado
        panel.add(userLabel);
        panel.add(userField);
        panel.add(passwordLabel);
        panel.add(passwordField);

        // Cria um diálogo de confirmação com OK e cancelar
        int confirm = JOptionPane.showConfirmDialog(
            null, panel, "Do you really want to connect with YOUR database?",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE
        );

        // Se não confirmar, joga erro de conexão
        if (confirm != JOptionPane.OK_OPTION) {
            throw new SQLException("Connection cancelled by the user.");
        }

        // Se confirmar, define as credenciais
        USER = userField.getText();
        PASSWORD = new String(passwordField.getPassword());
    }
}