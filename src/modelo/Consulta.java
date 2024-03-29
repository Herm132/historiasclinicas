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
    private MotivoConsulta mconsulta = null;

    public void motivoConsultaPaciente2(String numCedula, DefaultTableModel modelo) {
        String sql = "SELECT m.\"id_MConsulta\",m.\"motivo_Consulta\"\n"
                + "FROM sesion s\n"
                + "JOIN paciente p ON s.\"id_Paciente\" = p.\"id_Paciente\"\n"
                + "JOIN mconsulta m ON s.\"id_Mconsulta\" = m.\"id_Mconsulta\"\n"
                + "WHERE p.\"num_Cedula\" = '" + numCedula + "'";

        try (Connection con = establecerConexion()) {
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

    public void motivoConsultaPaciente(String numCedula, DefaultTableModel modelo) {
        String sql = "SELECT m.\"motivo_Consulta\"\n"
                + "FROM sesion s\n"
                + "JOIN paciente p ON s.\"id_Paciente\" = p.\"id_Paciente\"\n"
                + "JOIN mconsulta m ON s.\"id_Mconsulta\" = m.\"id_Mconsulta\"\n"
                + "WHERE p.\"num_Cedula\" = '" + numCedula + "'";
        try (Connection con = establecerConexion()) {
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

    public void guardarCita(Sesion secion, String contenido) {
        String sql = "INSERT INTO public.sesion(\n"
                + "\"id_Paciente\", \"id_Mconsulta\", \"fecha_Sesion\", descripcion)\n"
                + "	VALUES (?, ?, ?, ?);";

        try (Connection con = establecerConexion()) {
            ps = con.prepareStatement(sql);
            ps.setInt(1, secion.getIdPaciente());
            ps.setInt(2, secion.getIdMConsulta());
            ps.setString(3, secion.getFecha());
            ps.setString(4, contenido);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public void listaPacientes(DefaultTableModel modelo) {
        String sql = "SELECT \"num_Cedula\",nombres ,apellidos FROM paciente";
        try (Connection con = establecerConexion()) {
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

    public void busquedaPacientes(DefaultTableModel modelo, String busco) {
        String sql = "SELECT \"num_Cedula\", nombres, apellidos\n"
                + "FROM paciente\n"
                + "WHERE nombres ILIKE '%" + busco + "%' \n"
                + "OR apellidos ILIKE '%" + busco + "%' "
                + "OR \"num_Cedula\" ILIKE '%" + busco + "%' ";
        try (Connection con = establecerConexion()) {
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

    public Paciente buscarPacineteID(String busco) {
        String sql = "SELECT *\n"
                + "FROM paciente\n"
                + "WHERE \"num_Cedula\"='" + busco + "' ";
        try (Connection con = establecerConexion()) {
            Paciente pac = null;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String numCedula = rs.getString("num_Cedula");
                String nombres = rs.getString("nombres");
                String apellidos = rs.getString("apellidos");
                String fechaNacimietno = rs.getString("fecha_Nacimiento");
                String sexo = rs.getString("sexo");
                String instruccion = rs.getString("intruccion");
                String estadoCivil = rs.getString("estado_Civil");
                String direcion = rs.getString("direccion");
                String numero1 = rs.getString("num_Telefono1");
                String numero2 = rs.getString("num_Telefono2");
                String correo = rs.getString("correo");
                String fechaRegistro = rs.getString("fecha_Registro");
                pac = new Paciente(numCedula, nombres, apellidos, fechaNacimietno, sexo, instruccion, estadoCivil, direcion, numero1, numero2, correo, fechaRegistro);
            }
            return pac;
        } catch (SQLException e) {
            System.err.println(e);
            return null;

        }
    }

    public boolean agregarPaciente(Paciente pac) {
        String sql = "INSERT INTO public.paciente(\"num_Cedula\", nombres, apellidos, \"fecha_Nacimiento\", sexo, intruccion, \"estado_Civil\", direccion, \"num_Telefono1\", \"num_Telefono2\", correo, \"fecha_Registro\")\n"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try (Connection con = establecerConexion()) {
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
            System.out.println(e);
            return false;
        }
    }

    public int obtenerIdPaciente(String cedula) {
        int id = 0;
        try (Connection con = establecerConexion()) {
            String sql = "SELECT \"id_Paciente\" FROM paciente WHERE \"num_Cedula\"= ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, cedula);
            rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id_Paciente");
                return id;
            } else {
                return id;
            }
        } catch (SQLException e) {
            System.err.println(e);
            return 0;

        }

    }

    public boolean crearSesion(Sesion sesion) {

        String sql = "INSERT INTO public.sesion(\n"
                + "\"id_Paciente\", \"id_Mconsulta\", \"fecha_Sesion\", descripcion)\n"
                + "	VALUES ( ?, ?, ?, ?);";
        try (Connection conect = establecerConexion()) {
            ps = conect.prepareStatement(sql);

            ps.setInt(1, sesion.getIdPaciente());

            ps.setInt(2, sesion.getIdMConsulta());

            ps.setString(3, sesion.getFecha());
            ps.setString(4, sesion.getDescripcion());

            ps.execute();
            return true;

        } catch (SQLException e) {
            System.out.println(e);
            return false;

        }
    }

    public boolean cadulaRepetida(String cedula) {
        try (Connection con = establecerConexion()) {
            String sql = "SELECT \"num_Cedula\" FROM paciente WHERE \"num_Cedula\"= ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, cedula);
            ps.setMaxRows(1);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            return true;
        }
    }

    public void crearMConsulta(String mconsulta) {
        String sql = "INSERT INTO mconsulta(\n"
                + " \"motivo_Consulta\")\n"
                + "	VALUES (?);";
        try (Connection conect = establecerConexion()) {
            ps = conect.prepareStatement(sql);
            ps.setString(1, mconsulta);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public MotivoConsulta getUltimaMConsulta() {

        String sql = "SELECT *\n"
                + "FROM public.mconsulta\n"
                + "ORDER BY \"id_Mconsulta\" DESC\n"
                + "LIMIT 1;";
        try (Connection conect = establecerConexion()) {
            ps = conect.prepareStatement(sql);

            rs = ps.executeQuery();

            if (rs.next()) {
                int idMConsulta = rs.getInt("id_Mconsulta");
                String mConsulta = rs.getString("motivo_Consulta");
                mconsulta = new MotivoConsulta(idMConsulta, mConsulta);
            }

            return mconsulta;
        } catch (SQLException e) {
            System.out.println(e);
            return mconsulta;
        }

    }

    public MotivoConsulta getMConsulta(String mConsulta) {

        String sql = "SELECT *\n"
                + "FROM public.mconsulta\n"
                + "WHERE \"motivo_Consulta\"= ?;";
        try (Connection conect = establecerConexion()) {
            ps = conect.prepareStatement(sql);
            ps.setString(1, mConsulta);
            rs = ps.executeQuery();

            if (rs.next()) {
                int idMConsulta = rs.getInt("id_Mconsulta");
                mConsulta = rs.getString("motivo_Consulta");
                mconsulta = new MotivoConsulta(idMConsulta, mConsulta);
            }

            return mconsulta;
        } catch (SQLException e) {
            System.out.println(e);
            return mconsulta;
        }

    }

    public boolean updateSesion(Sesion sesion, String contenido) {
        String sql = "UPDATE sesion\n"
                + "	SET  descripcion=?\n"
                + "	WHERE \"id_Paciente\"=? AND \"id_Mconsulta\" =? AND \"descripcion\"=?;";
        try (Connection conect = establecerConexion()) {
            ps = conect.prepareStatement(sql);
            ps.setString(1, sesion.getDescripcion());
            ps.setInt(2, sesion.getIdPaciente());
            ps.setInt(3, sesion.getIdMConsulta());
            ps.setString(4, contenido);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }

    }

    public String descripcionPorId(int idPaciente, int idMConsulta) {
        String contenidio = null;

        String sql = "SELECT descripcion FROM sesion WHERE  \"id_Paciente\"=? AND \"id_Mconsulta\" =?";
        try (Connection conect = establecerConexion()) {
            ps = conect.prepareStatement(sql);
            ps.setInt(1, idPaciente);
            ps.setInt(2, idMConsulta);

            rs = ps.executeQuery();

            if (rs.next()) {
                contenidio = rs.getString("descripcion");

            }

            return contenidio;
        } catch (SQLException e) {
            System.out.println(e);
            return contenidio;
        }

    }

    public boolean cargarDescripcion(Sesion sesion, String contenido) {
        String sql = "UPDATE sesion\n"
                + "	SET  descripcion=?\n"
                + "	WHERE \"descripcion\"=?;";
        try (Connection conect = establecerConexion()) {
            ps = conect.prepareStatement(sql);
            ps.setString(1, sesion.getDescripcion());
            ps.setString(2, contenido);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean actualizarPaciente(Paciente pac, int id) {
        String sql = "UPDATE public.paciente\n"
                + "	SET \"num_Cedula\"=?, nombres=?, apellidos=?,"
                + " \"fecha_Nacimiento\"=?, sexo=?, intruccion=?,"
                + " \"estado_Civil\"=?, direccion=?, \"num_Telefono1\"=?,"
                + " \"num_Telefono2\"=?, correo=?, \"fecha_Registro\"=?\n"
                + "	WHERE \"id_Paciente\"=?;";
        try (Connection conect = establecerConexion()) {
            ps = conect.prepareStatement(sql);
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
            ps.setInt(13, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
}
