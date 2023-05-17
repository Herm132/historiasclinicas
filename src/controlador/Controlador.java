package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import modelo.Consulta;

import vista.VistaNuevoPaciente;


/**
 *
 * @author Kevin
 */
public class Controlador implements ActionListener {

    private final Consulta consulta;
    private final VistaNuevoPaciente vNuevoPaciente;

    public Controlador(Consulta consulta, VistaNuevoPaciente vNuevoPaciente) {
        this.consulta = consulta;

        this.vNuevoPaciente = vNuevoPaciente;

        this.vNuevoPaciente.jButtonGuardar.addActionListener(this);
    }

    public void iniciar() {
        
        vNuevoPaciente.setTitle("Nuevo Paciente");
        vNuevoPaciente.setResizable(false);
        vNuevoPaciente.setLocationRelativeTo(null);
    }

    private String fechaActual() {
        Date fechaActual = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        String fechaActualFormateada = formatoFecha.format(fechaActual);

        return fechaActualFormateada;

    }

    private String formatoFecha(Date fechaSeleccionada) {

        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        String fechaSelecionadaFormateada = formatoFecha.format(fechaSeleccionada);
        return fechaSelecionadaFormateada;
    }

    @Override
    public void actionPerformed(ActionEvent e) {


    }

}
