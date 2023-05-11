package vista;

import java.awt.Color;
import javax.swing.*;

public class CustomOptionPane {
    public static void main(String[] args) {
        // Crear un panel personalizado con fondo rojo
        JPanel panel = new JPanel();
        panel.setBackground(Color.RED);

        // Crear un JOptionPane personalizado con el panel rojo
        JOptionPane optionPane = new JOptionPane();
        optionPane.setMessage(panel);
        optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);

        // Mostrar el JOptionPane personalizado
        JDialog dialog = optionPane.createDialog(null, "Mensaje personalizado");
        dialog.setVisible(true);
    }
}
