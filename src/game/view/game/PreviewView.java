package game.view.game;

import game.controller.Controller;
import game.model.form.Form;
import game.view.View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class PreviewView extends View {

   private static final int WIDTH  = 200;
   private static final int HEIGHT = 350;
   
   private Form nextForm = null;

   public PreviewView(Controller controller) {
      super(controller);

      this.initialize();
   }

   private void initialize() {
      this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
   }

   @Override
   public void paintComponent(Graphics g) {
      super.paintComponent(g);

      Graphics2D g2d = (Graphics2D) g;

      if(this.nextForm != null){
      g2d.setColor(this.nextForm.getColor());
      }
      
      g2d.drawRect(25, 10, 150, 150);
   }

   public void updateView(Form nextForm) {
      this.nextForm = nextForm;
   }
}
