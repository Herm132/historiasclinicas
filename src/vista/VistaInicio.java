package vista;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import modelo.Consulta;
import modelo.MotivoConsulta;
import modelo.Paciente;

/**
 *
 * @author Harold
 */
public final class VistaInicio extends javax.swing.JFrame {

    private VistaNuevoPaciente vistanp = null;
    private VistaActualizarPaciente vistaap = null;
    private Consulta consulta = null;
    private Paciente nprec = null;
    private VistaPaciente vistap = null;
    private MotivoConsulta mconsulta = null;
    private VistaBusqueda vistab = null;
    private VistaMotivoConsulta vistamc = null;

    public VistaInicio() {
        initComponents();

        ImageIcon icono = new ImageIcon(getClass().getResource("/Imagenes/Logo.png"));

        this.setIconImage(icono.getImage());

        this.setVisible(true);
        this.setTitle("Inicio");
        this.setLocationRelativeTo(null);

        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                mensaje();
            }
        });
        setCustomCursor(jLabelSalir);
        setCustomCursor(btnSalir);
        setCustomCursor(btnBuscar);
        setCustomCursor(btnNPaciente);
        setCustomCursor(btnInicio);
        setCustomCursor(jLabelBuscar);
        setCustomCursor(jLabelInicio);
        setCustomCursor(jLabelNPaciente);
        modeloTabla();

        JTableHeader encabezado = this.jTableDatos.getTableHeader();
        encabezado.setFont(new Font("Roboto", Font.BOLD, 14)); // Tipo de letra: Arial, Negrita, Tamaño: 16

        // Establecer la instancia de JTableHeader en la tabla
        this.jTableDatos.setTableHeader(encabezado);

        // Deshabilitar el movimiento de columnas
        this.jTableDatos.getTableHeader().setReorderingAllowed(false);
    }

    public void mensaje() {
        int respuesta = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que quieres salir?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

        if (respuesta == JOptionPane.YES_OPTION) {

            System.exit(0);
        }
    }

    public static void setCustomCursor(JLabel label) {
        label.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(MouseEvent e) {
                label.setCursor(Cursor.getDefaultCursor());
            }
        });
    }

    public void btnVer() {

        // Obtener el índice de la columna "num_Cedula"
        int columna = 0; // asumimos que "num_Cedula" es la primera columna (índice 0)

        // Obtener el valor de la celda seleccionada en la columna "num_Cedula"
        int filaSeleccionada = jTableDatos.getSelectedRow(); // asumimos que "tabla" es el nombre de tu JTable
        String identificador = jTableDatos.getValueAt(filaSeleccionada, columna).toString();

        nprec = new Paciente();
        consulta = new Consulta();
        nprec = consulta.buscarPacineteID(identificador);

        this.dispose();

        vistamc = new VistaMotivoConsulta();

        vistamc.setVisible(true);

        modeloTabla2(vistamc, identificador);

        vistamc.txtFechaRegistro.setText(nprec.getFechaRegistro());

        vistamc.txtNumCedula.setText(nprec.getNumCedula());

        vistamc.txtNombres.setText(nprec.getNombres());

        vistamc.txtApellidos.setText(nprec.getApellidos());

        vistamc.txtEdad.setText(calularAnios(nprec.getFechaNacimiento()));

        vistamc.txtSexo.setText(nprec.getSexo());
        vistamc.txtEstadoCivil.setText(nprec.getEstadoCivil());

        vistamc.txtInstruccion.setText(nprec.getInstruccion());

        vistamc.txtDireccion.setText(nprec.getDireccion());

        vistamc.txtTef1.setText(nprec.getNumTelefono1());
        vistamc.txtTef2.setText(nprec.getNumTelefono2());

        vistamc.txtCorreo.setText(nprec.getCorreo());

    }

    private String calularAnios(String fecha) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Convertir la cadena de fecha en un objeto LocalDate
        LocalDate fechaNacimiento = LocalDate.parse(fecha, formatter);

        // Obtener la fecha actual
        LocalDate fechaActual = LocalDate.now();

        // Calcular la diferencia entre las dos fechas
        Period periodo = Period.between(fechaNacimiento, fechaActual);

        // Obtener la edad actual en años
        fecha = String.valueOf(periodo.getYears());

        // Imprimir la edad actual
        return fecha;

    }

    public void btnEditar() {

        // Obtener el índice de la columna "num_Cedula"
        int columna = 0; // asumimos que "num_Cedula" es la primera columna (índice 0)

        // Obtener el valor de la celda seleccionada en la columna "num_Cedula"
        int filaSeleccionada = jTableDatos.getSelectedRow(); // asumimos que "tabla" es el nombre de tu JTable
        String identificador = jTableDatos.getValueAt(filaSeleccionada, columna).toString();

        nprec = new Paciente();
        consulta = new Consulta();
        nprec = consulta.buscarPacineteID(identificador);

        this.dispose();

        vistaap = new VistaActualizarPaciente();

        vistaap.setVisible(true);

        vistaap.txtFecha.setText(nprec.getFechaRegistro());

        vistaap.jTextNumCedula.setText(nprec.getNumCedula());

        vistaap.jTextNombres.setText(nprec.getNombres());

        vistaap.jTextApellido.setText(nprec.getApellidos());

        String stringFecha = nprec.getFechaNacimiento();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date fecha = null;

        try {
            fecha = formato.parse(stringFecha);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        vistaap.jDateFechaNaci.setDate(fecha);

        vistaap.jComboBoxSexo.setSelectedItem(nprec.getSexo());

        vistaap.jComboBoxEstadoCivil.setSelectedItem(nprec.getEstadoCivil());

        vistaap.jComboBoxInstruccion.setSelectedItem(nprec.getInstruccion());

        vistaap.jTextDireccion.setText(nprec.getDireccion());

        vistaap.jTextTelefono1.setText(nprec.getNumTelefono1());
        vistaap.jTextTelefono2.setText(nprec.getNumTelefono2());

        vistaap.jTextCorreo.setText(nprec.getCorreo());
        vistaap.numCedula = identificador;

    }

    public void modeloTabla2(VistaMotivoConsulta vmc, String identificador) {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Permitir la edición solo en la columna de "Acciones" (columna 3)
                return column == 1;
            }
        };

        modelo.addColumn("Motivo de Consulta");
        modelo.addColumn("Acciones");

        consulta.motivoConsultaPaciente(identificador, modelo);

        vmc.jTableDatos.setModel(modelo);

        TablaEventos2 event = new TablaEventos2() {
            @Override
            public void onView(int row) {
                vmc.dispose();

                consulta = new Consulta();
                int columna = 0; // asumimos que "num_Cedula" es la primera columna (índice 0)

                // Obtener el valor de la celda seleccionada en la columna "num_Cedula"
                int filaSeleccionada = vmc.jTableDatos.getSelectedRow(); // asumimos que "tabla" es el nombre de tu JTable
                String mConsulta = vmc.jTableDatos.getValueAt(filaSeleccionada, columna).toString();

                //Hcaer que le mcosulta sea unico y no se repita
                //Buscamos por el nombre y obtenemos el id y ya pasamos a la vista p
                mconsulta = consulta.getMConsulta(mConsulta);

                nprec = consulta.buscarPacineteID(identificador);

                vistap = new VistaPaciente();

                String contenidoAntes = consulta.descripcionPorId(consulta.obtenerIdPaciente(identificador), mconsulta.getIdMConsulta());
                vistap.jTextAreaHistoria.setText(contenidoAntes);

                vistap.jIdMconsulta.setText(Integer.toString(mconsulta.getIdMConsulta()));
                vistap.setVisible(true);
                vistap.txtCedula.setText(identificador);
                vistap.txtNombres.setText(nprec.getNombres());
                vistap.txtApellidos.setText(nprec.getApellidos());
                vistap.txtEdad.setText(calularAnios(nprec.getFechaNacimiento()));
                vistap.txtSexo.setText(nprec.getSexo());
                vistap.txtInstruccion.setText(nprec.getInstruccion());
                vistap.txtMConsulta.setText(mConsulta);
            }

        };
        vmc.jTableDatos.getColumnModel().getColumn(1).setCellRenderer(new CeldaRender2());
        vmc.jTableDatos.getColumnModel().getColumn(1).setCellEditor(new CeldaEditor2(event));

        JTableHeader encabezado = vmc.jTableDatos.getTableHeader();
        encabezado.setFont(new Font("Roboto", Font.BOLD, 14)); // Tipo de letra: Arial, Negrita, Tamaño: 16

        // Establecer la instancia de JTableHeader en la tabla
        vmc.jTableDatos.setTableHeader(encabezado);

        // Deshabilitar el movimiento de columnas
        vmc.jTableDatos.getTableHeader().setReorderingAllowed(false);
    }

    public void modeloTabla() {
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
        consulta = new Consulta();
        consulta.listaPacientes(modelo);

        this.jTableDatos.setModel(modelo);

        TablaEventos1 event = new TablaEventos1() {

            @Override
            public void onView(int row) {
                btnVer();
            }

            @Override
            public void onEdit(int row) {

                btnEditar();
            }
        };

        this.jTableDatos.getColumnModel().getColumn(3).setCellRenderer(new CeldaRender1());
        this.jTableDatos.getColumnModel().getColumn(3).setCellEditor(new CeldaEditor1(event));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fondo = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableDatos = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        jLabelTitulo = new javax.swing.JLabel();
        jLabelSalir = new javax.swing.JLabel();
        btnSalir = new javax.swing.JLabel();
        jLabelInicio = new javax.swing.JLabel();
        btnInicio = new javax.swing.JLabel();
        jLabelNPaciente = new javax.swing.JLabel();
        btnNPaciente = new javax.swing.JLabel();
        jLabelBuscar = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        fondo.setBackground(new java.awt.Color(255, 255, 255));

        jTableDatos.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jTableDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cédula", "Nombres", "Apellidos", "Acciones"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableDatos.setGridColor(new java.awt.Color(255, 255, 255));
        jTableDatos.setRowHeight(40);
        jTableDatos.setSelectionBackground(new java.awt.Color(101, 196, 246));
        jScrollPane1.setViewportView(jTableDatos);

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        jPanel2.setBackground(new java.awt.Color(101, 196, 246));

        jLabelTitulo.setBackground(new java.awt.Color(255, 255, 255));
        jLabelTitulo.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        jLabelTitulo.setText("BIENVENIDO DR.CÉSAR PIEDRA");
        jLabelTitulo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(371, Short.MAX_VALUE)
                .addComponent(jLabelTitulo)
                .addContainerGap(372, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelTitulo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabelSalir.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabelSalir.setText("Salir");
        jLabelSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelSalirMouseClicked(evt);
            }
        });

        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/exit20x20.png"))); // NOI18N
        btnSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSalirMouseClicked(evt);
            }
        });

        jLabelInicio.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabelInicio.setText("Inicio");
        jLabelInicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelInicioMouseClicked(evt);
            }
        });

        btnInicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/home20x20.png"))); // NOI18N
        btnInicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnInicioMouseClicked(evt);
            }
        });

        jLabelNPaciente.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabelNPaciente.setText("Nuevo Paciente");
        jLabelNPaciente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelNPacienteMouseClicked(evt);
            }
        });

        btnNPaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/user-add20x20.png"))); // NOI18N
        btnNPaciente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNPacienteMouseClicked(evt);
            }
        });

        jLabelBuscar.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabelBuscar.setText("Buscar");
        jLabelBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelBuscarMouseClicked(evt);
            }
        });

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/search20x20.png"))); // NOI18N
        btnBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBuscarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout fondoLayout = new javax.swing.GroupLayout(fondo);
        fondo.setLayout(fondoLayout);
        fondoLayout.setHorizontalGroup(
            fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(fondoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(fondoLayout.createSequentialGroup()
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(fondoLayout.createSequentialGroup()
                        .addComponent(btnInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(fondoLayout.createSequentialGroup()
                        .addComponent(btnNPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelNPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jSeparator1)
                    .addGroup(fondoLayout.createSequentialGroup()
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        fondoLayout.setVerticalGroup(
            fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fondoLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(fondoLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelInicio)
                            .addComponent(btnInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnNPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelNPaciente))
                        .addGap(18, 18, 18)
                        .addGroup(fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelBuscar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelSalir)))
                    .addGroup(fondoLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fondo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalirMouseClicked
        mensaje();
    }//GEN-LAST:event_btnSalirMouseClicked

    private void jLabelSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelSalirMouseClicked
        mensaje();
    }//GEN-LAST:event_jLabelSalirMouseClicked

    private void jLabelInicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelInicioMouseClicked
        this.dispose();
        this.setVisible(true);

    }//GEN-LAST:event_jLabelInicioMouseClicked

    private void btnInicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInicioMouseClicked
        this.dispose();
        this.setVisible(true);
    }//GEN-LAST:event_btnInicioMouseClicked

    private void jLabelNPacienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelNPacienteMouseClicked
        this.dispose();
        vistanp = new VistaNuevoPaciente();
        vistanp.setVisible(true);

    }//GEN-LAST:event_jLabelNPacienteMouseClicked

    private void btnNPacienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNPacienteMouseClicked
        this.dispose();
        vistanp = new VistaNuevoPaciente();
        vistanp.setVisible(true);
    }//GEN-LAST:event_btnNPacienteMouseClicked

    private void jLabelBuscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBuscarMouseClicked
        this.dispose();
        vistab = new VistaBusqueda();
        vistab.setVisible(true);
    }//GEN-LAST:event_jLabelBuscarMouseClicked

    private void btnBuscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarMouseClicked
        this.dispose();
        vistab = new VistaBusqueda();
        vistab.setVisible(true);
    }//GEN-LAST:event_btnBuscarMouseClicked

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VistaInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaInicio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel btnBuscar;
    public javax.swing.JLabel btnInicio;
    public javax.swing.JLabel btnNPaciente;
    public javax.swing.JLabel btnSalir;
    private javax.swing.JPanel fondo;
    public javax.swing.JLabel jLabelBuscar;
    public javax.swing.JLabel jLabelInicio;
    public javax.swing.JLabel jLabelNPaciente;
    public javax.swing.JLabel jLabelSalir;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    public javax.swing.JTable jTableDatos;
    // End of variables declaration//GEN-END:variables
}
