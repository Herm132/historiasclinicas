package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import modelo.Consulta;
import modelo.Paciente;
import vista.VistaNuevoPacientexzxcx;

/**
 *
 * @author Kevin
 */
public class Controlador implements ActionListener {

    private final Consulta consulta;
    private final VistaNuevoPacientexzxcx vNuevoPaciente;

    public Controlador(Consulta consulta, VistaNuevoPacientexzxcx vNuevoPaciente) {
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
        Paciente aux;
        if (e.getSource() == vNuevoPaciente.jButtonGuardar) {

            aux = new Paciente(
                    vNuevoPaciente.jTextNumCedula.getText(),
                    vNuevoPaciente.jTextNombres.getText(),
                    vNuevoPaciente.jTextApellido.getText(),
                    formatoFecha(vNuevoPaciente.jDateFechaNaci.getDate()),
                    vNuevoPaciente.jComboBoxSexo.getSelectedItem().toString(),
                    vNuevoPaciente.jComboBoxInstruccion.getSelectedItem().toString(),
                    vNuevoPaciente.jComboBoxEstadoCivil.getSelectedItem().toString(),
                    vNuevoPaciente.jTextDireccion.getText(),
                    vNuevoPaciente.jTextTelefono1.getText(),
                    vNuevoPaciente.jTextTelefono2.getText(),
                    vNuevoPaciente.jTextCorreo.getText(),
                    fechaActual());
            


            if (consulta.agregarPaciente(aux)) {

                //Ocultar y contrar a otra vista
            }else{
            
                    
            }

        }

    }

}
