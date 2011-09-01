package game.view;

import game.controller.MenuController;
import game.enums.Resources;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class MenuView extends View implements ActionListener {

   private final static int WIDTH              = 400;
   private final static int HEIGHT             = 600;

   private JButton          newGameButton      = new JButton(Resources.NEWGAME.getString());
   private JButton          continueGameButton = new JButton(Resources.CONTINUEGAME.getString());
   private JButton          configButton       = new JButton(Resources.CONFIGURATION.getString());
   private JButton          closeGameButton    = new JButton(Resources.CLOSEGAME.getString());

   public MenuView(MenuController controller) {
      super(controller, WIDTH, HEIGHT);
      this.initalize();
   }

   private void initalize() {
      GridLayout layout = new GridLayout(10, 1);
      layout.setVgap(20);
      this.setLayout(layout);

      this.continueGameButton.setEnabled(false);

      this.add(this.newGameButton);
      this.add(this.continueGameButton);
      this.add(this.configButton);
      this.add(this.closeGameButton);
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      if (e.getSource() == this.newGameButton) {
         this.getController().onNewGame();
      }
      else if (e.getSource() == this.continueGameButton) {
         this.getController().onContinueGame();
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

   public void setGameRunning(boolean b) {
      this.continueGameButton.setEnabled(b);
   }
}
