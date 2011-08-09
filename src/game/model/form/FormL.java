package game.model.form;

import game.model.FormUnit;

import java.awt.Color;
import java.util.ArrayList;

public class FormL extends Form {

   public FormL(int columnIndex, int rowIndex) {
      super(columnIndex, rowIndex);
   }

   @Override
   public Color getColor() {
      return Color.blue;
   }

   @Override
   public ArrayList<FormUnit> generateUnits(int columnIndex, int rowIndex) {
      ArrayList<FormUnit> generatedUnits = new ArrayList<FormUnit>();
      
      generatedUnits.add(new FormUnit(columnIndex + 0, rowIndex + 2));
      generatedUnits.add(new FormUnit(columnIndex + 1, rowIndex + 0));
      generatedUnits.add(new FormUnit(columnIndex + 1, rowIndex + 1));
      generatedUnits.add(new FormUnit(columnIndex + 1, rowIndex + 2));
      
      return generatedUnits;
   }

   @Override
   public int getCurrentHeight() {
      return 3;
      //TODO
   }

   @Override
   public int getCurrentWidth() {
      return 2;
      //TODO
   }

}
