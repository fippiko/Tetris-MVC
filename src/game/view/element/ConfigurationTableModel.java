package game.view.element;

import game.enums.ConfigurationAttribute;

import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.table.AbstractTableModel;

public class ConfigurationTableModel extends AbstractTableModel {
   private int      addedRows   = 0;
   final String[]   columnNames = {"Attribute", "Value", "Default"};
   final Object[][] data;
   private Hashtable<ConfigurationAttribute, Integer> rowToAttributeMap = new Hashtable<ConfigurationAttribute, Integer>();
   
   public ConfigurationTableModel(int rowCount){
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

   

   /*
    * Don't need to implement this method unless your table's editable.
    */
   public boolean isCellEditable(int row, int col) {
      // Note that the data/cell address is constant,
      // no matter where the cell appears onscreen.
      if (col < 2) {
         return false;
      }
      else {
         return true;
      }
   }
   
   public Object getValueFrom(ConfigurationAttribute attribute) {
      return data[this.rowToAttributeMap.get(attribute)][1];
   }


   public void addNewRow(ConfigurationAttribute attribute, String attributeValue) {
      data[this.getRowCount()][0] = attribute.toString();
      data[this.getRowCount()][1] = attributeValue;
      data[this.getRowCount()][2] = attribute.getDefaultValue();
      
      rowToAttributeMap.put(attribute, this.addedRows);
      
      this.addedRows++;
   }

   @Override
   public Object getValueAt(int arg0, int arg1) {
        return data[arg0][arg1];
   }
}
