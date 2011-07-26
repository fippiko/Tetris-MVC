package game.view.game;

import game.view.Element;

import java.util.ArrayList;

public class GameGrid extends Element {
   private ArrayList<GameGridUnit> gridRects = new ArrayList<GameGridUnit>();

   public GameGrid(int x, int y, int width, int height, int columnCount, int rowCount) {
      super(x, y, width, height);

      this.gridRects = this.generateGridUnits(width, height, columnCount, rowCount);
   }

   public ArrayList<GameGridUnit> generateGridUnits(int gridWidth, int gridHeight, int columnCount, int rowCount) {
      ArrayList<GameGridUnit> generatedGridUnits = new ArrayList<GameGridUnit>();
      
      int unitWidth = gridWidth / columnCount;
      int unitHeight = gridHeight / rowCount;
      
      for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
         for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
            int unitX = this.getX() + unitWidth * columnIndex;
            int unitY = this.getY() + unitHeight * rowIndex;

            generatedGridUnits.add(new GameGridUnit(unitX, unitY, unitWidth, unitHeight, columnIndex, rowIndex));
         }
      }

      return generatedGridUnits;
   }

   public GameGridUnit getUnit(int columnIndex, int rowIndex) {
      for (GameGridUnit unit : this.gridRects) {
         if (unit.getColumnIndex() == columnIndex && unit.getRowIndex() == rowIndex) {
            return unit;
         }
      }

      return null;
   }

   public ArrayList<GameGridUnit> getUnits() {
      return this.gridRects;
   }
}
