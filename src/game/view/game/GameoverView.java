package game.view.game;

import game.controller.Controller;
import game.view.View;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class GameoverView extends View {
   
   private static final int WIDTH    = 600;
   private static final int HEIGHT   = 600;

   public GameoverView(Controller controller) {
      super(controller);
      
      this.setSize(WIDTH, HEIGHT);
   }

   @Override
   protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      
      Graphics2D g2d = (Graphics2D) g;

      g2d.drawString("Ohhhh no... game is over!", 100, 100);
      
      g2d.drawString("Press SPACE to restart the game", 100, 200);
   }
}
