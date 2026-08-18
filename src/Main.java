import javax.swing.*; // Importa todos os componentes gráficos do Swing

import java.awt.*; // Importa o pacote gráfico base do Java (Swing roda em cima dele)

public class Main {
    public static void main(String[] args) {
        // 0. Instanciação das classes de componentes e lógicas
        InterfaceComponents ic = new InterfaceComponents();
        InterfaceLogic il = new InterfaceLogic();

        // 1. Criação do frame (janela)
        JFrame frame = new JFrame("Store CRUD"); // Cria uma nova janela
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define que o clique no X, fecha a janela
        frame.setSize(360, 360); // Define o tamanho da janela
        frame.setLocationRelativeTo(null); // Centraliza em relação ao centro (null = centro)

        // 2. Criação do panel (container que guarda os itens e fica dentro do frame)
        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10)); // Cria esse painel e define a organização do elementos
        // 5 linhas, 1 coluna (um item por linha), 10 de espaço vertical e 10 de espaço horizontal
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Seta a borda, colocando uma margem vazia de 20px (tipo um padding no CSS)

        // 3. Criação dos componentes
        JLabel title = ic.createStyledText("Store CRUD", "title", "center"); // Cria o título

        JButton insertBtn = ic.createStyledButton("Insert item"); // Botão de inserir itens
        insertBtn.addActionListener(e -> il.insert());

        JButton listBtn = ic.createStyledButton("List items"); // Botão de listar itens
        listBtn.addActionListener(e -> il.list());

        JButton updateBtn = ic.createStyledButton("Update item"); // Botão de atualizar itens
        updateBtn.addActionListener(e -> il.update());

        JButton deleteBtn = ic.createStyledButton("Delete item"); // Botão de deletar itens
        deleteBtn.addActionListener(e -> il.delete());

        // 4. Adição dos componentes no painel
        panel.add(title);
        panel.add(insertBtn);
        panel.add(listBtn);
        panel.add(updateBtn);
        panel.add(deleteBtn);

        // 5. Adição do painel no frame
        frame.add(panel);
        // Faz o frame fica vísivel, ativando a interface
        frame.setVisible(true);
    }
}