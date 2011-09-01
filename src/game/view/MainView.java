package game.view;

import game.controller.Controller;

import javax.swing.border.EmptyBorder;

public class MainView extends View {

   private static final int WIDTH  = 800;
   private static final int HEIGHT = 600;

   public MainView(Controller controller) {
      super(controller, WIDTH, HEIGHT);
      this.initialize();
   }

   public void initialize() {
      setBorder(new EmptyBorder(0, 0, 0, 0));
   }
}
