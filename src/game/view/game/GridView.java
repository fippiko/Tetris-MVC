package game.view.game;

import game.controller.ControllerBase;
import game.view.View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public abstract class GridView extends View {
   private Grid grid;

   public GridView(ControllerBase controller, int width, int height, int colCount, int rowCount) {
      super(controller);

      this.setPreferredSize(new Dimension(width, height));

      this.setGrid(new Grid(0, 0, width, height, colCount, rowCount));
   }

   protected void paint(Graphics2D g2d, GridUnit gridUnit, Color borderColor, Color formColor, boolean fill) {
      g2d.setColor(borderColor);
      g2d.drawRect(gridUnit.getX(), gridUnit.getY(), gridUnit.getWidth(), gridUnit.getHeight());

      if (fill) {
         g2d.setColor(formColor);
         g2d.fillRect(gridUnit.getX() + 1, gridUnit.getY() + 1, gridUnit.getWidth() - 1, gridUnit.getHeight() - 1);
      }
   }

   @Override
   public void paintComponent(Graphics g) {
      super.paintComponent(g);

      Graphics2D g2d = (Graphics2D) g;

      for (GridUnit gridUnit : this.getGrid().getUnits()) {
         this.paint(g2d, gridUnit, Color.black, null, false);
      }
   }

   protected Grid getGrid() {
      return grid;
   }

   protected void setGrid(Grid grid) {
      this.grid = grid;
   }
}
