package vista;

/**
 *
 * @author Harold
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;

public class Inicio {

    public static void main(String[] args) {

        String imagePath = "/Imagenes/fondo1.jpg";

        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setUndecorated(true);
        jFrame.setSize(800, 500);

        JPanel panel = new JPanel(new BorderLayout());

        try {

            BufferedImage image = ImageIO.read(Inicio.class.getResource(imagePath));
            Image scaledImage = image.getScaledInstance(jFrame.getSize().width, jFrame.getSize().height, Image.SCALE_SMOOTH);
            ImageIcon imageIcon = new ImageIcon(scaledImage);
            JLabel imageLabel = new JLabel(imageIcon);
            panel.add(imageLabel, BorderLayout.CENTER);
        } catch (IOException e) {
            System.out.println("Error: "+e);
        }

        JProgressBar progressBar = new JProgressBar();
        progressBar.setMinimum(0);
        progressBar.setMaximum(100);
        progressBar.setStringPainted(true);
        progressBar.setString("Cargando...");
        Color customColor = new Color(0, 191, 255); // Creamos un nuevo color celeste
        progressBar.setForeground(customColor); // Establecemos el nuevo color como color de la barra de carga
        progressBar.repaint(); // Actualizamos la barra de carga

        panel.add(progressBar, BorderLayout.SOUTH);

        Timer timer = new Timer(200, new ActionListener() {
            int counter = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                counter++;
                progressBar.setValue(counter * 10);
               
                if (counter == 10) {
                    jFrame.dispose();
                }
            }
        });
        timer.start();

        jFrame.setContentPane(panel);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);

    }
}
