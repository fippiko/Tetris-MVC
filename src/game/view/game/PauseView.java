package game.view.game;

import game.controller.GameController;
import game.view.View;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class PauseView extends View {

   public PauseView(GameController controller, final int width, final int height) {
      super(controller, width, height);
   }

   @Override
   protected void paintComponent(Graphics g) {
      super.paintComponent(g);

      Graphics2D g2d = (Graphics2D) g;

      g2d.drawString("Game paused...", 200, 200);

      g2d.drawString("Continue with p...", 200, 400);
   }
}
