package historiasclinicas;

import controlador.ControlListaPacientes;
import controlador.Controlador;
import modelo.Consulta;
import vista.VistaInicio;
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
        VistaInicio view2 = new VistaInicio();
        


        Controlador ctrl = new Controlador(con, view1);
        ControlListaPacientes c = new ControlListaPacientes(con, view2);
//        ctrl.iniciar();
        c.iniciar();
//
//        view1.setVisible(true);
    }
    
}
