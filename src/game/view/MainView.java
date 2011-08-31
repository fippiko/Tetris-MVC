package game.view;

import game.controller.ControllerBase;

import java.awt.Dimension;

import javax.swing.border.EmptyBorder;

public class MainView extends View {

   private static final int WIDTH  = 800;
   private static final int HEIGHT = 600;

   public MainView(ControllerBase controller) {
      super(controller);
      this.initialize();
   }

   public void initialize() {
      this.setPreferredSize(new Dimension(WIDTH, HEIGHT));

      setBorder(new EmptyBorder(0, 0, 0, 0));
   }
}
