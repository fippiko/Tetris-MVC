package game.controller;

import game.view.MenuView;

import java.awt.event.KeyEvent;

public class MenuController extends Controller {
   MainController parentController;

   public MenuController(MainController parentController) {
      this.parentController = parentController;
      this.setView(new MenuView(this));
   }

   @Override
   public void executeKey(KeyEvent keyEvent) {
      super.executeKey(keyEvent);

      if (keyEvent.getKeyCode() == KeyEvent.VK_ESCAPE) {
         this.close();
      }
   }

   public void onNewGame() {
      this.parentController.showNewGame();
   }

   public void onConfiguration() {
      this.parentController.showConfiguration();
   }

   public void onCloseGame() {
      this.close();
   }
}
