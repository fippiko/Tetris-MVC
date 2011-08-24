package game.model.form;

import game.model.FormMap;

import java.awt.Color;

public class FormI extends Form {

   public FormI(int columnIndex, int rowIndex) {
      super(columnIndex, rowIndex);
   }

   @Override
   public Color getColor() {
      return Color.blue;
   }

   @Override
   public FormMap getDefaultFormMap() {
      FormMap formMap = new FormMap();

      formMap.add(0, 0);
      formMap.add(0, 1);
      formMap.add(0, 2);
      formMap.add(0, 3);
      
      formMap.setRotateAxis(0, 2);

      return formMap;
   }

}
