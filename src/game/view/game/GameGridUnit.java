package game.view.game;

import game.view.Element;


public class GameGridUnit extends Element {
   private int     rowIndex;
   private int     columnIndex;

   public GameGridUnit(int x, int y, int width, int height, int columnIndex, int rowIndex) {
      super(x, y, width, height);

      this.rowIndex = rowIndex;
      this.columnIndex = columnIndex;
   }

   public int getRowIndex() {
      return this.rowIndex;
   }

   public int getColumnIndex() {
      return this.columnIndex;
   }
}
