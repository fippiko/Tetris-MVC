package game.view.game;

import game.controller.Controller;
import game.view.View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GameGridView extends View {
   private int      width  = 600;
   private int      height = 600;

   private GameGrid grid;

   public GameGridView(Controller controller) {
      super(controller);

      this.setPreferredSize(new Dimension(this.width, this.height));
      
      this.grid = new GameGrid(0, 0, this.width, this.height, 18, 18);
   }

   @Override
   public void paintComponent(Graphics g) {
      super.paintComponent(g);

      Graphics2D g2d = (Graphics2D) g;

      for (GameGridUnit gridUnit : this.grid.getUnits()) {
         this.paint(g2d, gridUnit, Color.black, false);
      }
   }

   private void paint(Graphics2D g2d, GameGridUnit gridUnit, Color color, Boolean fill) {
      // draw the border
      g2d.drawRect(gridUnit.getX(), gridUnit.getY(), gridUnit.getWidth(), gridUnit.getHeight());

      if (fill) {
         g2d.setColor(color);
         g2d.fillRect(gridUnit.getX() + 1, gridUnit.getY() + 1, gridUnit.getWidth() - 1, gridUnit.getHeight() - 1);
      }
   }
}
