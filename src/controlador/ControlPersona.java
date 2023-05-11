/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import vista.VistaNuevoPacientexzxcx;

/**
 *
 * @author Harold
 */
public class ControlPersona {

    private final VistaNuevoPacientexzxcx npaciente;

    public ControlPersona(VistaNuevoPacientexzxcx npaciente) {

        this.npaciente = npaciente;
    }

    public void iniciar() {
        npaciente.setTitle("Nuevo Paciente");

        npaciente.setLocationRelativeTo(null);

    }
}
