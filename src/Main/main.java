package Main;

import Menu.MenuPart2;
import javax.swing.UIManager;

public class main {
    public static void main(String[] args) {
        // Tạo và hiển thị giao diện MenuPart2
        java.awt.EventQueue.invokeLater(() -> {
            new MenuPart2().setVisible(true);
        });
    }
        
    
    
}
