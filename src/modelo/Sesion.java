/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Harold
 */
public class Sesion {
    private int idPaciente;
    private int idMConsulta;
    private String descripcion;
    private String fecha;

    public Sesion(int idPaciente, int idMConsulta, String descripcion, String fecha) {
        this.idPaciente = idPaciente;
        this.idMConsulta = idMConsulta;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public int getIdMConsulta() {
        return idMConsulta;
    }

    public void setIdMConsulta(int idMConsulta) {
        this.idMConsulta = idMConsulta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    
    
    
    
    
    
    
    
}
