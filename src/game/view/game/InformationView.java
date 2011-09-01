package game.view.game;

import game.controller.Controller;
import game.view.View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class InformationView extends View {
   private int score = 0;
   private int level = 0;

   public InformationView(Controller controller, final int width, final int height) {
      super(controller, width, height);
   }

   @Override
   public void paintComponent(Graphics g) {
      super.paintComponent(g);

      Graphics2D g2d = (Graphics2D) g;

      g2d.setColor(Color.black);
      g2d.drawRect(25, 20, 150, 150);
      
      g2d.drawString("Current score:" + this.score, 50, 40);
      g2d.drawString("Current level:" + this.level, 50, 80);
   }

   public void updateScore(int score) {
      this.score = score;
   }
   
   public void updateLevel (int level){
      this.level = level;
   }
}
