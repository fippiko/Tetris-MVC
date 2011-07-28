package game.view.configuration;

import game.model.Configuration;
import game.model.ConfigurationAttributeMap;

import java.lang.reflect.Array;
import java.util.Hashtable;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;

public class ConfigurationTableModel extends AbstractTableModel {
   private final static int                           COLUMN_NAME       = 0;
   private final static int                           COLUMN_VALUE      = 1;
   private final static int                           COLUMN_DEFAULT    = 2;

   private int                                        addedRows         = 0;
   final String[]                                     columnNames       = {"Attribute", "Value", "Default"};
   final Object[][]                                   data;
   private Hashtable<Configuration, Integer> rowToAttributeMap = new Hashtable<Configuration, Integer>();

   public ConfigurationTableModel(int rowCount) {
      data = new Object[rowCount][columnNames.length];
   }

   public int getColumnCount() {
      return columnNames.length;
   }

   public int getRowCount() {
      return addedRows;
   }

   public String getColumnName(int col) {
      return columnNames[col];
   }

   public void setValueAt(Object value, int row, int col) {
      data[row][col] = value;
      fireTableCellUpdated(row, col);
   }

   public boolean isCellEditable(int row, int col) {
      if (col == COLUMN_VALUE) {
         return true;
      }

      return false;
   }

   public Object getValueFrom(Configuration attribute) {
      return data[this.rowToAttributeMap.get(attribute)][1];
   }

   public void addNewRow(Configuration attribute, String attributeValue) {
      data[this.getRowCount()][COLUMN_NAME] = attribute.toString();
      data[this.getRowCount()][COLUMN_VALUE] = attributeValue.length() == 0 ? attribute.getDefaultValue() : attributeValue;
      data[this.getRowCount()][COLUMN_DEFAULT] = attribute.getDefaultValue();

      rowToAttributeMap.put(attribute, this.addedRows);

      fireTableCellUpdated(this.addedRows, COLUMN_VALUE);

      this.addedRows++;
   }

   @Override
   public Object getValueAt(int row, int col) {
      return data[row][col];
   }

   public TableCellEditor getCellEditor(int row, int col) {
      TableCellEditor editor = null;

      Configuration attribute = this.getConfigurationAttributeFrom(row);

      if (attribute != null) {
         if (attribute.getType() == Array.class) {
            JComboBox comboBox = new JComboBox();
            for (String option : attribute.getOptions()) {
               comboBox.addItem(option);
            }
            comboBox.setSelectedItem(attribute.getDefaultValue());

            editor = new DefaultCellEditor(comboBox);
         }
      }

      return editor;
   }

   private Configuration getConfigurationAttributeFrom(int row) {
      for (Configuration attribute : this.rowToAttributeMap.keySet()) {
         if (this.rowToAttributeMap.get(attribute) == row) {
            return attribute;
         }
      }

      return null;
   }

   public ConfigurationAttributeMap getConfigurationAttributes() {
      ConfigurationAttributeMap attributeMap = new ConfigurationAttributeMap();

      for (Configuration attribute : this.rowToAttributeMap.keySet()) {
         String newValue = this.getValueFrom(attribute).toString();
         attributeMap.put(attribute, newValue);
      }

      return attributeMap;
   }
}
