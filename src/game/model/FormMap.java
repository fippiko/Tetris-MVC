package game.model;

import java.util.ArrayList;
import java.util.Hashtable;

public class FormMap extends Hashtable<Integer, ArrayList<Integer>> {
   
   private int rotateAxisColumn = -1;
   private int rotateAxisRow = -1;
   
   public void add(final int columnIndex, final int rowIndex) {
      if (!this.containsKey(columnIndex)) {
         this.put(columnIndex, new ArrayList<Integer>());
      }

      this.get(columnIndex).add(rowIndex);
   }
   
   public void setRotateAxis(final int columnIndex, final int rowIndex){
      this.rotateAxisColumn = columnIndex;
      this.rotateAxisRow = rowIndex;
   }
   
   public int getRotateAxisColumn(){
      return this.rotateAxisColumn;
   }
   
   public int getRotateAxisRow(){
      return this.rotateAxisRow;
   }
}
