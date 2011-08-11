package game.model.form;

import game.model.FormMap;

import java.awt.Color;

public class FormL extends Form {

   public FormL(int columnIndex, int rowIndex) {
      super(columnIndex, rowIndex);
   }

   @Override
   public Color getColor() {
      return Color.blue;
   }

   @Override
   public FormMap getFormMap() {
      FormMap formMap = new FormMap();

      formMap.add(0, 2);
      formMap.add(1, 0);
      formMap.add(1, 1);
      formMap.add(1, 2);
      
      formMap.setRotateAxis(1, 1);

      return formMap;
   }

}
