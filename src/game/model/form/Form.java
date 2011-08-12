package game.model.form;

import game.model.FormMap;
import game.model.FormUnit;

import java.awt.Color;
import java.util.ArrayList;

public abstract class Form {
   private ArrayList<FormUnit> units;
   private FormUnit            rotateAxisUnit;

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

   public void rotate() {
      for (FormUnit unit : this.getUnits()) {
         if (this.rotateAxisUnit != null) {
            if (unit != this.rotateAxisUnit) {
               int deltaColumn = this.rotateAxisUnit.getColumn() - unit.getColumn();
               int deltaRow = this.rotateAxisUnit.getRow() - unit.getRow();

               int finalColumn = unit.getColumn() + deltaColumn + deltaRow;
               int finalRow = unit.getRow() - deltaColumn + deltaRow;

               unit.setColumn(finalColumn);
               unit.setRow(finalRow);
            }
         }
      }
   }

   private ArrayList<FormUnit> generateUnits(int startColumnIndex, int startRowIndex) {
      ArrayList<FormUnit> generatedFormUnits = new ArrayList<FormUnit>();

      FormMap formMap = this.getFormMap();

      for (Integer columnIndex : formMap.keySet()) {
         for (Integer rowIndex : formMap.get(columnIndex)) {
            FormUnit newFormUnit = new FormUnit(startColumnIndex + columnIndex, startRowIndex + rowIndex);
            generatedFormUnits.add(newFormUnit);

            if (columnIndex == formMap.getRotateAxisColumn()) {
               if (rowIndex == formMap.getRotateAxisRow()) {
                  this.rotateAxisUnit = newFormUnit;
               }
            }
         }
      }

      return generatedFormUnits;
   }

   public abstract Color getColor();
   public abstract FormMap getFormMap();
}
