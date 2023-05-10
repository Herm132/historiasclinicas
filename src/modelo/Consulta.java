package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Harold
 */
public class Consulta extends Conexion {

    private ResultSet rs = null;
    private PreparedStatement ps = null;

    public void listaPacientes(DefaultTableModel modelo) {

        Connection con = establecerConexion();
        String sql = "SELECT \"num_Cedula\",nombres ,apellidos FROM paciente";

        try {

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            ResultSetMetaData resultado = rs.getMetaData();
            int cantidadColumnas = resultado.getColumnCount();
            while (rs.next()) {
                Object[] filas = new Object[cantidadColumnas];
          
                for (int i = 0; i < cantidadColumnas; i++) {
                    filas[i] = rs.getObject(i + 1);
                }
                modelo.addRow(filas);
            }

        } catch (SQLException e) {
            System.err.println(e);

        } 
  
    }

    public boolean agregarPaciente(Paciente pac) {
        Connection con = establecerConexion();
        String sql = "INSERT INTO public.paciente(\"num_Cedula\", nombres, apellidos, \"fecha_Nacimiento\", sexo, intruccion, \"estado_Civil\", direccion, \"num_Telefono1\", \"num_Telefono2\", correo, \"fecha_Registro\")\n"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            ps = con.prepareStatement(sql);

            ps.setString(1, pac.getNumCedula());
            ps.setString(2, pac.getNombres());
            ps.setString(3, pac.getApellidos());
            ps.setString(4, pac.getFechaNacimiento());
            ps.setString(5, pac.getSexo());
            ps.setString(6, pac.getInstruccion());
            ps.setString(7, pac.getEstadoCivil());
            ps.setString(8, pac.getDireccion());
            ps.setString(9, pac.getNumTelefono1());
            ps.setString(10, pac.getNumTelefono2());
            ps.setString(11, pac.getCorreo());
            ps.setString(12, pac.getFechaRegistro());

            ps.execute();
            return true;

        } catch (SQLException e) {
            System.err.println(e);
            return false;

        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e);
            }

        }

    }

//    public Persona buscarPersona(String cedula) {
//
//        Persona encontro = new Persona();
//
//        for (Persona x : listapersonas) {
//            if (cedula.equalsIgnoreCase(x.getCedula())) {
//                encontro = x;
//            }
//        }
//
//        return encontro;
//    }
//
    public int obtenerIdPaciente() {
        int resultado = 0;
        try {
            Connection con = establecerConexion();
            String sql = "SELECT * FROM paciente";
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet r = statement.executeQuery();
            while (r.next()) {
                resultado++;
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e);

        }
        return resultado + 1;
    }
//
//    public ArrayList<Persona> getListapersonas() {
//        return listapersonas;
//    }
//
//    public void setListapersonas(ArrayList<Persona> listapersonas) {
//        this.listapersonas = listapersonas;
//    }

}
