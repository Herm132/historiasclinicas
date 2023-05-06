/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import vista.VistaNuevoPaciente;

/**
 *
 * @author Harold
 */
public class ControlPersona {

    private final VistaNuevoPaciente npaciente;

    public ControlPersona(VistaNuevoPaciente npaciente) {

        this.npaciente = npaciente;
    }

    public void iniciar() {
        npaciente.setTitle("Nuevo Paciente");

        npaciente.setLocationRelativeTo(null);

    }
}
