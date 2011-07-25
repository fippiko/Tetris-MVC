package game.view;

import game.controller.Controller;

import java.awt.Dimension;

import javax.swing.border.EmptyBorder;

public class MainView extends View {

   private int width  = 800;
   private int height = 600;

   public MainView(Controller controller) {
      super(controller);
      this.initialize();
   }

   public void initialize() {
      this.setPreferredSize(new Dimension(this.width, this.height));

      setBorder(new EmptyBorder(0, 0, 0, 0));
   }
}
