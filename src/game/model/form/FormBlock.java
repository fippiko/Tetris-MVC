package game.model.form;

import java.awt.Color;

public class FormBlock extends Form {

   public FormBlock(int columnIndex, int rowIndex) {
      super(columnIndex, rowIndex);
   }

   @Override
   public Color getColor() {
      return Color.gray;
   }

   public int[][] getFormMap() {
      int[][] map = new int[MAXFORMSIZE][MAXFORMSIZE];

      map[0][0] = 1;
      map[0][1] = 1;
      map[1][0] = 1;
      map[1][1] = 1;

      return map;
   }

   @Override
   public int getCurrentHeight() {
      return 2;
   }

   @Override
   public int getCurrentWidth() {
      return 2;
   }
}
