package game.view.element;

import game.view.Element;


public class GridUnit extends Element {
   private int     rowIndex;
   private int     columnIndex;

   public GridUnit(int x, int y, int width, int height, int columnIndex, int rowIndex) {
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
