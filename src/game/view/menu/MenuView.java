package game.view.menu;

import game.controller.MenuController;
import game.enums.Resources;
import game.view.View;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;


public class MenuView extends View implements ActionListener {

   private int     width           = 400;
   private int     height          = 600;

   private JButton newGameButton   = new JButton(Resources.NEWGAME.getString());
   private JButton configButton    = new JButton(Resources.CONFIGURATION.getString());
   private JButton closeGameButton = new JButton(Resources.CLOSEGAME.getString());

   public MenuView(MenuController controller) {
      super(controller);
      this.initalize();
   }

   private void initalize() {
      this.setPreferredSize(new Dimension(this.width, this.height));

      GridLayout layout = new GridLayout(10, 1);
      layout.setVgap(20);
      this.setLayout(layout);

      this.add(this.newGameButton);
      this.add(this.configButton);
      this.add(this.closeGameButton);
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      if (e.getSource() == this.newGameButton) {
         this.getController().onNewGame();
      }
      else if (e.getSource() == this.configButton) {
         this.getController().onConfiguration();
      }
      else if (e.getSource() == this.closeGameButton) {
         this.getController().onCloseGame();
      }
   }

   @Override
   protected MenuController getController() {
      return (MenuController) super.getController();
   }
}
