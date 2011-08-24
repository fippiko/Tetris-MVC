package game.model.form;

import game.model.FormMap;

import java.awt.Color;

public class FormBlock extends Form {

   public FormBlock(int columnIndex, int rowIndex) {
      super(columnIndex, rowIndex);
   }

   @Override
   public Color getColor() {
      return Color.GRAY;
   }

   @Override
   public FormMap getDefaultFormMap() {
      FormMap formMap = new FormMap();

      formMap.add(0, 0);
      formMap.add(0, 1);
      formMap.add(1, 0);
      formMap.add(1, 1);

      return formMap;
   }
}
