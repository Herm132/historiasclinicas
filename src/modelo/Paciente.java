/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Harold
 */
public class Paciente {


    private String numCedula;
    private String nombres;
    private String apellidos;
    
    private String fechaNacimiento;
    private String sexo;
    private String instruccion;
    private String estadoCivil;
    private String direccion;
    private String numTelefono1;
    private String numTelefono2;
    private String correo;
    private String fechaRegistro;

    public Paciente() {
    }

    public Paciente(String numCedula, String nombres, String apellidos, String fechaNacimiento, String sexo, String instruccion, String estadoCivil, String direccion, String numTelefono1, String numTelefono2, String correo, String fechaRegistro) {

        this.numCedula = numCedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.instruccion = instruccion;
        this.estadoCivil = estadoCivil;
        this.direccion = direccion;
        this.numTelefono1 = numTelefono1;
        this.numTelefono2 = numTelefono2;
        this.correo = correo;
        this.fechaRegistro = fechaRegistro;
    }

    @Override
    public String toString() {
        return "Paciente{" + "numCedula=" + numCedula + ", nombres=" + nombres + ", apellidos=" + apellidos + ", fechaNacimiento=" + fechaNacimiento + ", sexo=" + sexo + ", instruccion=" + instruccion + ", estadoCivil=" + estadoCivil + ", direccion=" + direccion + ", numTelefono1=" + numTelefono1 + ", numTelefono2=" + numTelefono2 + ", correo=" + correo + ", fechaRegistro=" + fechaRegistro + '}';
    }



    public String getNumCedula() {
        return numCedula;
    }

    public void setNumCedula(String numCedula) {
        this.numCedula = numCedula;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getInstruccion() {
        return instruccion;
    }

    public void setInstruccion(String instruccion) {
        this.instruccion = instruccion;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNumTelefono1() {
        return numTelefono1;
    }

    public void setNumTelefono1(String numTelefono1) {
        this.numTelefono1 = numTelefono1;
    }

    public String getNumTelefono2() {
        return numTelefono2;
    }

    public void setNumTelefono2(String numTelefono2) {
        this.numTelefono2 = numTelefono2;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }


}
