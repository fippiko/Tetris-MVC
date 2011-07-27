package game.model.form;

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
   public int[][] getFormMap() {
      int[][] map = new int[MAXFORMSIZE][MAXFORMSIZE];

      map[0][2] = 1;
      map[1][0] = 1;
      map[1][1] = 1;
      map[1][2] = 1;

      return map;
   }

   @Override
   public int getCurrentHeight() {
      return 3;
   }

}
