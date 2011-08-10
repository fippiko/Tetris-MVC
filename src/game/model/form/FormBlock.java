package game.model.form;

import game.model.FormUnit;

import java.awt.Color;
import java.util.ArrayList;

public class FormBlock extends Form {

   public FormBlock(int columnIndex, int rowIndex) {
      super(columnIndex, rowIndex);
   }

   @Override
   public Color getColor() {
      return Color.gray;
   }

   @Override
   public ArrayList<FormUnit> generateUnits(int columnIndex, int rowIndex) {
      ArrayList<FormUnit> generatedUnits = new ArrayList<FormUnit>();
      
      generatedUnits.add(new FormUnit(columnIndex + 0, rowIndex + 0));
      generatedUnits.add(new FormUnit(columnIndex + 0, rowIndex + 1));
      generatedUnits.add(new FormUnit(columnIndex + 1, rowIndex + 0));
      generatedUnits.add(new FormUnit(columnIndex + 1, rowIndex + 1));
      
      return generatedUnits;
   }
}
