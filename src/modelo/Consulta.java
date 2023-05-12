package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Harold
 */
public class Consulta extends Conexion {
    
    private ResultSet rs = null;
    private PreparedStatement ps = null;
    
    public void guardarCita(Sesion secion, String contenido) {
        Connection con = establecerConexion();
        String sql = "INSERT INTO public.sesion(\n"
                + "\"id_Paciente\", \"id_Mconsulta\", \"fecha_Sesion\", descripcion)\n"
                + "	VALUES (?, ?, ?, ?);";
        
        try {
            
            ps = con.prepareStatement(sql);
            ps.setInt(1, secion.getIdPaciente());
            ps.setInt(2, secion.getIdMConsulta());
            ps.setString(3, secion.getFecha());
            ps.setString(4, contenido);
            
            ps.executeUpdate();
            
        } catch (SQLException e) {
            System.err.println(e);
            
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
            
        }
        
    }
    
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
            
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
            
        }
        
    }
    
    public void busquedaPacientes(DefaultTableModel modelo, String busco) {
        
        Connection con = establecerConexion();
        String sql = "SELECT \"num_Cedula\", nombres, apellidos\n"
                + "FROM paciente\n"
                + "WHERE nombres ILIKE '%" + busco + "%' \n"
                + "OR apellidos ILIKE '%" + busco + "%' "
                + "OR \"num_Cedula\" ILIKE '%" + busco + "%' ";
        
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
            
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
            
        }
        
    }
    
    public Paciente buscarPacineteID(String busco) {
        
        Connection con = establecerConexion();
        String sql = "SELECT *\n"
                + "FROM paciente\n"
                + "WHERE \"num_Cedula\"='" + busco + "' ";
        
        try {
            Paciente pac = null;            
            
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {                
                String numCedula = rs.getString("num_Cedula");
                String nombres = rs.getString("nombres");
                String apellidos = rs.getString("apellidos");
                String fechaNacimietno = rs.getString("apellidos");
                String sexo = rs.getString("apellidos");
                String instruccion = rs.getString("apellidos");
                String estadoCivil = rs.getString("apellidos");
                String direcion = rs.getString("apellidos");
                String numero1 = rs.getString("apellidos");
                String numero2 = rs.getString("apellidos");
                String correo = rs.getString("apellidos");
                String fechaRegistro = rs.getString("apellidos");
                pac = new Paciente(numCedula, nombres, apellidos, fechaNacimietno, sexo, instruccion, estadoCivil, direcion, numero1, numero2, correo, fechaRegistro);
                
            }
            
            return pac;
            
        } catch (SQLException e) {
            System.err.println(e);
            return null;
            
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
            
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
