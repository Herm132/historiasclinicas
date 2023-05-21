package vista;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

/**
 *
 * @author RAVEN
 */
public class CeldaEditor2 extends DefaultCellEditor {

   private TablaEventos2 event;

    public CeldaEditor2(TablaEventos2 event) {
        super(new JCheckBox());
      this.event = event;
    }

    @Override
    public Component getTableCellEditorComponent(JTable jtable, Object o, boolean bln, int row, int column) {
        Botones2 action = new Botones2();
       action.initEvent(event, row);
        action.setBackground(jtable.getSelectionBackground());
        return action;
    }
}
