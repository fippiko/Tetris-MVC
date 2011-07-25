package game.view;

import java.awt.Component;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
   public MainFrame() {
      this.initialize();
   }

   public void initialize() {
      this.setTitle("Tetris");
      this.setLocation(100, 100);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setResizable(false);
      this.pack();
      this.setVisible(true);
   }

   @Override
   public Component add(final Component comp) {
      final Component compReturn = super.add(comp);
      this.pack();
      return compReturn;
   }
}
