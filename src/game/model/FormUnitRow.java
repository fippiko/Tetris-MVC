package game.model;

import java.util.ArrayList;

public class FormUnitRow extends ArrayList<FormUnit> {
   private int rowIndex = 0;

   @Override
   public boolean add(FormUnit e) {
      this.rowIndex = e.getRow();

      return super.add(e);
   }

   public int getRow() {
      return this.rowIndex;
   }

   public void setBlinkState(boolean b) {
      for (FormUnit unit : this) {
         unit.setBlinkState(b);
      }
   }
}
