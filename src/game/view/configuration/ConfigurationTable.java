package game.view.configuration;

import game.model.ConfigurationAttributeMap;

import java.awt.Color;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

public class ConfigurationTable extends JTable {
   private JScrollPane        scrollPane;

   private final static int   ROWHEIGHT      = 40;

   private final static Color COLOR_READONLY = Color.LIGHT_GRAY;
   private final static Color COLOR_DEFAULT  = Color.WHITE;

   public ConfigurationTable() {
      this.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
      this.setAutoResizeMode(AUTO_RESIZE_LAST_COLUMN);
      this.setRowHeight(ROWHEIGHT);
   }

   public JScrollPane generateScrollPane() {
      this.scrollPane = new JScrollPane(this);

      this.scrollPane.setBounds(this.getBounds());

      return this.scrollPane;
   }

   @Override
   public void setModel(TableModel dataModel) {
      super.setModel(dataModel);

      if (this.scrollPane != null) {
         this.scrollPane.setBounds(this.getBounds());
      }
   }

   @Override
   public TableCellRenderer getCellRenderer(int row, int column) {
      DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) super.getCellRenderer(row, column);

      if (this.getModel().isCellEditable(row, column)) {
         renderer.setBackground(COLOR_DEFAULT);
      }
      else {
         renderer.setBackground(COLOR_READONLY);
      }

      return renderer;
   }

   @Override
   public TableCellEditor getCellEditor(int row, int col) {
      TableCellEditor tmpEditor = ((ConfigurationTableModel) this.getModel()).getCellEditor(row, col);
      if (tmpEditor != null) {
         return tmpEditor;
      }
      return super.getCellEditor(row, col);
   }

   public void updateUI() {
      super.updateUI();
   }

   public ConfigurationAttributeMap getConfigurationAttributes() {
      return ((ConfigurationTableModel) this.getModel()).getConfigurationAttributes();
   }
}
