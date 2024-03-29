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
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import modelo.Consulta;
import modelo.MotivoConsulta;
import modelo.Paciente;
import modelo.Sesion;

/**
 *
 * @author Harold
 */
public final class VistaMotivoConsulta extends javax.swing.JFrame {

    private VistaNuevoPaciente vistaNP = null;
    private VistaInicio vistaI = null;
    private Consulta consulta = null;
    private MotivoConsulta mconsulta = null;
    private Sesion sesion = null;
    private VistaPaciente vistap = null;
    private Paciente nprec = null;
    private VistaBusqueda vistab = null;

    public VistaMotivoConsulta() {
        initComponents();

        ImageIcon icono = new ImageIcon(getClass().getResource("/Imagenes/Logo.png"));

        this.setIconImage(icono.getImage());

        this.setVisible(true);
        this.setTitle("Motivo Consulta");
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
        setCustomCursor(txtCorreo);
        setCustomCursor(txtNumCedula);

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

    public void modeloTabla() {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Permitir la edición solo en la columna de "Acciones" (columna 3)
                return column == 1;
            }
        };

        modelo.addColumn("Motivo de Consulta");
        modelo.addColumn("Acciones");

        consulta.motivoConsultaPaciente(txtNumCedula.getText(), modelo);

        this.jTableDatos.setModel(modelo);

        TablaEventos2 event = new TablaEventos2() {

            @Override
            public void onView(int row) {
                dispose();
                vistap = new VistaPaciente();
                nprec = new Paciente();
                consulta = new Consulta();

                int columna = 0; // asumimos que "num_Cedula" es la primera columna (índice 0)

                // Obtener el valor de la celda seleccionada en la columna "num_Cedula"
                int filaSeleccionada = jTableDatos.getSelectedRow(); // asumimos que "tabla" es el nombre de tu JTable
                String mConsulta = jTableDatos.getValueAt(filaSeleccionada, columna).toString();

                mconsulta = consulta.getUltimaMConsulta();

                nprec = consulta.buscarPacineteID(txtNumCedula.getText());

                vistap.jIdMconsulta.setText(Integer.toString(mconsulta.getIdMConsulta()));
                vistap.setVisible(true);
                vistap.txtCedula.setText(txtNumCedula.getText());
                vistap.txtNombres.setText(nprec.getNombres());
                vistap.txtApellidos.setText(nprec.getApellidos());
                vistap.txtEdad.setText(calularAnios(nprec.getFechaNacimiento()));
                vistap.txtSexo.setText(nprec.getSexo());
                vistap.txtInstruccion.setText(nprec.getInstruccion());
                vistap.txtMConsulta.setText(mConsulta);

            }
        };
        this.jTableDatos.getColumnModel().getColumn(1).setCellRenderer(new CeldaRender2());
        this.jTableDatos.getColumnModel().getColumn(1).setCellEditor(new CeldaEditor2(event));

        JTableHeader encabezado = jTableDatos.getTableHeader();
        encabezado.setFont(new Font("Roboto", Font.BOLD, 14)); // Tipo de letra: Roboto, Negrita, Tamaño: 16

        // Establecer la instancia de JTableHeader en la tabla
        this.jTableDatos.setTableHeader(encabezado);

        // Deshabilitar el movimiento de columnas
        this.jTableDatos.getTableHeader().setReorderingAllowed(false);
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
        jButton1 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtFechaRegistro = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtApellidos = new javax.swing.JLabel();
        txtNombres = new javax.swing.JLabel();
        txtNumCedula = new javax.swing.JLabel();
        txtEdad = new javax.swing.JLabel();
        txtSexo = new javax.swing.JLabel();
        txtInstruccion = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JLabel();
        txtTef2 = new javax.swing.JLabel();
        txtTef1 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JLabel();
        txtEstadoCivil = new javax.swing.JLabel();
        jTextMConsulta = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        fondo.setBackground(new java.awt.Color(255, 255, 255));

        jTableDatos.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jTableDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Modtivo de Consulta", "Acciones"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, true
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
        jLabelTitulo.setText("MOTIVO DE CONSULTA");
        jLabelTitulo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelTitulo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        jButton1.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jButton1.setText("Agregar Nuevo Motivo de Consulta");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel13.setText("Teléfono 1");

        jLabel14.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel14.setText("Teléfono 2");

        jLabel15.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel15.setText("Correo");

        jLabel16.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel16.setText("Fecha de Ingreso del Paciente");

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel1.setText("Número de Cédula ");

        txtFechaRegistro.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtFechaRegistro.setText("fecha de registro");

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel2.setText("Nombres");

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel3.setText("Apellidos");

        jLabel4.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel4.setText("Edad");

        jLabel9.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel9.setText("Sexo");

        jLabel10.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel10.setText("Instrucción");

        jLabel11.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel11.setText("Estado Civil");

        jLabel12.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel12.setText("Dirección");

        txtApellidos.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtApellidos.setText("Apellidos");

        txtNombres.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtNombres.setText("Nombres");

        txtNumCedula.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtNumCedula.setText("  ");
        txtNumCedula.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNumCedulaMouseClicked(evt);
            }
        });

        txtEdad.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtEdad.setText("Edad");

        txtSexo.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtSexo.setText("Sexo");

        txtInstruccion.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtInstruccion.setText("Instrucción");

        txtCorreo.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtCorreo.setText("Correo");
        txtCorreo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtCorreoMouseClicked(evt);
            }
        });

        txtTef2.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtTef2.setText("Teléfono 2");

        txtTef1.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtTef1.setText("Teléfono 1");

        txtDireccion.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtDireccion.setText("Dirección");

        txtEstadoCivil.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtEstadoCivil.setText("Estado Civil");

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(fondoLayout.createSequentialGroup()
                        .addComponent(jTextMConsulta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addGroup(fondoLayout.createSequentialGroup()
                        .addGroup(fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addGroup(fondoLayout.createSequentialGroup()
                                .addGroup(fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel9))
                                .addGap(27, 27, 27)
                                .addGroup(fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNumCedula, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                    .addComponent(txtSexo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtInstruccion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtEdad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtApellidos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtNombres, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtFechaRegistro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(128, 128, 128)
                        .addGroup(fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel12)
                                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCorreo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTef2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTef1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtEstadoCivil, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                            .addComponent(txtDireccion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        fondoLayout.setVerticalGroup(
            fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fondoLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(fondoLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
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
                            .addComponent(jLabelBuscar)))
                    .addGroup(fondoLayout.createSequentialGroup()
                        .addGroup(fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(txtFechaRegistro))
                        .addGap(18, 18, 18)
                        .addGroup(fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(fondoLayout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel13)
                                .addGap(41, 41, 41)
                                .addComponent(jLabel15))
                            .addGroup(fondoLayout.createSequentialGroup()
                                .addGroup(fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(fondoLayout.createSequentialGroup()
                                        .addComponent(txtNumCedula)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtNombres)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtApellidos))
                                    .addGroup(fondoLayout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel2)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel3)))
                                .addGap(12, 12, 12)
                                .addComponent(jLabel4)
                                .addGap(16, 16, 16)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel10))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fondoLayout.createSequentialGroup()
                                .addComponent(txtEdad)
                                .addGap(16, 16, 16)
                                .addComponent(txtSexo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtInstruccion))
                            .addGroup(fondoLayout.createSequentialGroup()
                                .addComponent(txtEstadoCivil)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtDireccion)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTef1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtTef2)
                                    .addComponent(jLabel14))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtCorreo)))))
                .addGap(18, 18, 18)
                .addGroup(fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextMConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(fondoLayout.createSequentialGroup()
                        .addGap(0, 154, Short.MAX_VALUE)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelSalir)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
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
            .addComponent(fondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        vistaI = new VistaInicio();
        vistaI.setVisible(true);
    }//GEN-LAST:event_jLabelInicioMouseClicked

    private void btnInicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInicioMouseClicked
        this.dispose();
        vistaI = new VistaInicio();
        vistaI.setVisible(true);
    }//GEN-LAST:event_btnInicioMouseClicked

    private void jLabelNPacienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelNPacienteMouseClicked
        this.dispose();
        vistaNP = new VistaNuevoPaciente();
        vistaNP.setVisible(true);
    }//GEN-LAST:event_jLabelNPacienteMouseClicked

    private void btnNPacienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNPacienteMouseClicked
        this.dispose();
        vistaNP = new VistaNuevoPaciente();
        vistaNP.setVisible(true);
    }//GEN-LAST:event_btnNPacienteMouseClicked

    private void txtCorreoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCorreoMouseClicked
        // Copiar el texto en el portapapeles
        StringSelection stringSelection = new StringSelection(txtCorreo.getText());
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
        // Mostrar mensaje al usuario
        JOptionPane.showMessageDialog(null, "Correo copiado al portapapeles");
    }//GEN-LAST:event_txtCorreoMouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        consulta = new Consulta();
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaActualTexto = fechaActual.format(formato);

        if (!"".equals(jTextMConsulta.getText())) {
            consulta.crearMConsulta(jTextMConsulta.getText());
            this.jTextMConsulta.setText("");
            mconsulta = consulta.getUltimaMConsulta();

            String contenido = "";
            sesion = new Sesion(consulta.obtenerIdPaciente(txtNumCedula.getText()), mconsulta.getIdMConsulta(), contenido, fechaActualTexto);
            if (consulta.crearSesion(sesion)) {
                JOptionPane.showMessageDialog(null, "Se agrego Motivo Consulta correctamente.", "Información", JOptionPane.INFORMATION_MESSAGE);
            }
            modeloTabla();
        } else {
            JOptionPane.showMessageDialog(null, "El MOTIVO CONSULTA esta vacio.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_jButton1MouseClicked

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

    private void txtNumCedulaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNumCedulaMouseClicked
                // Copiar el texto en el portapapeles
        StringSelection stringSelection = new StringSelection(txtNumCedula.getText());
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
        // Mostrar mensaje al usuario
        JOptionPane.showMessageDialog(null, "Cédula copiada al portapapeles");
    }//GEN-LAST:event_txtNumCedulaMouseClicked
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VistaMotivoConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaMotivoConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaMotivoConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaMotivoConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaMotivoConsulta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel btnBuscar;
    public javax.swing.JLabel btnInicio;
    public javax.swing.JLabel btnNPaciente;
    public javax.swing.JLabel btnSalir;
    private javax.swing.JPanel fondo;
    private javax.swing.JButton jButton1;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel10;
    public javax.swing.JLabel jLabel11;
    public javax.swing.JLabel jLabel12;
    public javax.swing.JLabel jLabel13;
    public javax.swing.JLabel jLabel14;
    public javax.swing.JLabel jLabel15;
    public javax.swing.JLabel jLabel16;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel4;
    public javax.swing.JLabel jLabel9;
    public javax.swing.JLabel jLabelBuscar;
    public javax.swing.JLabel jLabelInicio;
    public javax.swing.JLabel jLabelNPaciente;
    public javax.swing.JLabel jLabelSalir;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    public javax.swing.JTable jTableDatos;
    private javax.swing.JTextField jTextMConsulta;
    public javax.swing.JLabel txtApellidos;
    public javax.swing.JLabel txtCorreo;
    public javax.swing.JLabel txtDireccion;
    public javax.swing.JLabel txtEdad;
    public javax.swing.JLabel txtEstadoCivil;
    public javax.swing.JLabel txtFechaRegistro;
    public javax.swing.JLabel txtInstruccion;
    public javax.swing.JLabel txtNombres;
    public javax.swing.JLabel txtNumCedula;
    public javax.swing.JLabel txtSexo;
    public javax.swing.JLabel txtTef1;
    public javax.swing.JLabel txtTef2;
    // End of variables declaration//GEN-END:variables
}
