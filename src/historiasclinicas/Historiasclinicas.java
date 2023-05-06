/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package historiasclinicas;

import controlador.Controlador;
import modelo.Conexion;
import modelo.Consulta;
import vista.VistaNuevoPaciente;

/**
 *
 * @author Harold
 */
public class Historiasclinicas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Consulta con = new Consulta();
        VistaNuevoPaciente view1 = new VistaNuevoPaciente();


        Controlador ctrl = new Controlador(con, view1);
        ctrl.iniciar();

        view1.setVisible(true);
    }
    
}
