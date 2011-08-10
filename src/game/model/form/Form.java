package game.model.form;

import game.model.FormUnit;

import java.awt.Color;
import java.util.ArrayList;

public abstract class Form {
   private ArrayList<FormUnit> units;

   public Form(int columnIndex, int rowIndex) {
      this.units = this.generateUnits(columnIndex, rowIndex);
   }

   public ArrayList<FormUnit> getUnits() {
      return this.units;
   }

   public void moveHorizontal(int horizontalDelta) {
      for (FormUnit unit : this.units) {
         unit.setColumn(unit.getColumn() + horizontalDelta);
      }
   }

   public void moveVertical(int verticalDelta) {
      for (FormUnit unit : this.units) {
         unit.setRow(unit.getRow() + verticalDelta);
      }
   }

   public abstract Color getColor();
   public abstract ArrayList<FormUnit> generateUnits(int columnIndex, int rowIndex);
}
