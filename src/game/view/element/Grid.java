package game.view.element;

import game.view.Element;

import java.awt.Color;
import java.util.ArrayList;

public class Grid extends Element {
   private ArrayList<GridUnit> gridRects = new ArrayList<GridUnit>();

   public Grid(int x, int y, int width, int height, int columnCount, int rowCount) {
      super(x, y, width, height);

      this.gridRects = this.generateGridUnits(width, height, columnCount, rowCount);
   }

   public ArrayList<GridUnit> generateGridUnits(int gridWidth, int gridHeight, int columnCount, int rowCount) {
      ArrayList<GridUnit> generatedGridUnits = new ArrayList<GridUnit>();
      
      int unitWidth = gridWidth / columnCount;
      int unitHeight = gridHeight / rowCount;
      
      for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
         for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
            int unitX = this.getX() + unitWidth * columnIndex;
            int unitY = this.getY() + unitHeight * rowIndex;

            generatedGridUnits.add(new GridUnit(unitX, unitY, unitWidth, unitHeight, columnIndex, rowIndex));
         }
      }

      return generatedGridUnits;
   }

   public GridUnit getUnit(int columnIndex, int rowIndex) {
      for (GridUnit unit : this.gridRects) {
         if (unit.getColumnIndex() == columnIndex && unit.getRowIndex() == rowIndex) {
            return unit;
         }
      }

      return null;
   }

   public ArrayList<GridUnit> getUnits() {
      return this.gridRects;
   }
}
