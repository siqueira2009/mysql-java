import javax.swing.*;
import java.awt.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InterfaceComponents {

    // Método para criar um botão estilizado
    public JButton createStyledButton(String text) {
        JButton btn = new JButton(text); // Cria o botão

        // Define as estilizações
        btn.setFont(new Font("Arial", Font.PLAIN, 18)); // Família, peso e tamanho da fonte 
        btn.setFocusPainted(false); // Desativa a borda ao clicar
        btn.setBackground(new Color(0xFFFFFF)); // Define a cor de fundo
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Define o cursor
        btn.setBorder(BorderFactory.createCompoundBorder( // Define a borda
            BorderFactory.createLineBorder(new Color(0xBDC3C7), 1), // 1px com cor cinza
            BorderFactory.createEmptyBorder(10, 20, 10, 20) // Define o padding
        ));

        // Adiciona um ouvinte no mouse
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { // Quando entrar no botão...
                btn.setBackground(new Color(0xE8E8E8)); // Muda a cor
            }

            public void mouseExited(MouseEvent e) { // Quando sair...
                btn.setBackground(new Color(0xFFFFFF)); // Volta a cor para o estado original
            }
        });

        // Retorna esse BTN
        return btn;
    } 

    // Método para criar um texto estilizado
    public JLabel createStyledText(String text, String type, String pos) {
        JLabel txt = new JLabel(text); // Cria um label
        
        // Verifica o tipo e com isso define o tipo do texto
        if (type == "title") {
            txt.setFont(new Font("Arial", Font.BOLD, 32));
        } else if (type == "subtitle") {
            txt.setFont(new Font("Arial", Font.BOLD, 24));
        } else {
            txt.setFont(new Font("Arial", Font.PLAIN, 24));    
        }
        
        // Verifica o posicionamento passado e com isso define a centralização
        if (pos == "left") {
            txt.setHorizontalAlignment(SwingConstants.LEFT);
        } else if (pos == "right") {
            txt.setHorizontalAlignment(SwingConstants.RIGHT);
        } else {
            txt.setHorizontalAlignment(SwingConstants.CENTER);
        }

        // Retorna esse TXT
        return txt;
    }
}