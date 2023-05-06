/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Harold
 */
public class Conexion {
     Connection conectar = null;
    String usuario = "postgres";
    String contrasenia = "12345";
    String db = "HCLINICA";
    String ip = "localhost";

    String Puerto = "5432";
    String cadena = "jdbc:postgresql://" + ip + ":" + Puerto + "/" + db;
   
    public Connection establecerConexion() {
      
        try {
            Class.forName("org.postgresql.Driver");
            conectar = (Connection) DriverManager.getConnection(cadena, usuario, contrasenia);
           
        } catch (HeadlessException | ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos, error:" + e.toString());
        }
        return conectar;
    }
    
}
