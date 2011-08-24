package game.model.form;

import game.model.FormMap;

import java.awt.Color;

public class FormT extends Form {

   public FormT(int columnIndex, int rowIndex) {
      super(columnIndex, rowIndex);
   }

   @Override
   public Color getColor() {
      return Color.PINK;
   }

   @Override
   public FormMap getDefaultFormMap() {
      FormMap formMap = new FormMap();

      formMap.add(0, 0);
      formMap.add(1, 0);
      formMap.add(2, 0);
      formMap.add(1, 1);

      formMap.setRotateAxis(1, 0);

      return formMap;
   }

}
