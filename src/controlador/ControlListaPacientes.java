package controlador;

import java.awt.Font;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import modelo.Consulta;
import vista.CeldaEditor;
import vista.CeldaRender;
import vista.TablaEventos;
import vista.VistaInicio;

/**
 *
 * @author Harold
 */
public class ControlListaPacientes {

    private final Consulta consulta;
    private final VistaInicio vistaInicio;

    public ControlListaPacientes(Consulta consulta, VistaInicio vistaInicio) {
        this.consulta = consulta;
        this.vistaInicio = vistaInicio;
    }

    public void iniciar() {
        vistaInicio.setVisible(true);
        vistaInicio.setTitle("Inicio");
        vistaInicio.setLocationRelativeTo(null);
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Permitir la edición solo en la columna de "Acciones" (columna 3)
                return column == 3;
            }
        };

        modelo.addColumn("Cédula");
        modelo.addColumn("Nombres");
        modelo.addColumn("Apellidos");
        modelo.addColumn("Acciones");
        consulta.listaPacientes(modelo);

        vistaInicio.jTableDatos.setModel(modelo);

        TablaEventos event = new TablaEventos() {
            @Override
            public void onEdit(int row) {
                System.out.println("Edit row : " + row);
            }

            @Override
            public void onView(int row) {
                System.out.println("View row : " + row);
            }
        };

        vistaInicio.jTableDatos.getColumnModel().getColumn(3).setCellRenderer(new CeldaRender());
        vistaInicio.jTableDatos.getColumnModel().getColumn(3).setCellEditor(new CeldaEditor(event));

        JTableHeader encabezado = vistaInicio.jTableDatos.getTableHeader();
        encabezado.setFont(new Font("Roboto", Font.BOLD, 14)); // Tipo de letra: Arial, Negrita, Tamaño: 16

// Establecer la instancia de JTableHeader en la tabla
        vistaInicio.jTableDatos.setTableHeader(encabezado);

        // Deshabilitar el movimiento de columnas
        vistaInicio.jTableDatos.getTableHeader().setReorderingAllowed(false);

    }

}
