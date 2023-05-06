package vista;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

/**
 *
 * @author RAVEN
 */
public class CeldaEditor extends DefaultCellEditor {

   private TablaEventos event;

    public CeldaEditor(TablaEventos event) {
        super(new JCheckBox());
      this.event = event;
    }

    @Override
    public Component getTableCellEditorComponent(JTable jtable, Object o, boolean bln, int row, int column) {
        Botones action = new Botones();
       action.initEvent(event, row);
        action.setBackground(jtable.getSelectionBackground());
        return action;
    }
}
