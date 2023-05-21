package vista;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

/**
 *
 * @author RAVEN
 */
public class CeldaEditor1 extends DefaultCellEditor {

   private TablaEventos1 event;

    public CeldaEditor1(TablaEventos1 event) {
        super(new JCheckBox());
      this.event = event;
    }

    @Override
    public Component getTableCellEditorComponent(JTable jtable, Object o, boolean bln, int row, int column) {
        Botones1 action = new Botones1();
       action.initEvent(event, row);
        action.setBackground(jtable.getSelectionBackground());
        return action;
    }
}
