package game.controller;

import game.view.menu.MenuView;

import java.awt.event.KeyEvent;

public class MenuController extends Controller {
   MainController parentController;
   
   public MenuController(MainController parentController) {
      this.parentController = parentController;
      this.setView(new MenuView(this));
   }

   @Override
   public void keyPressed(KeyEvent keyEvent) {
      super.keyPressed(keyEvent);

      if (keyEvent.getKeyCode() == KeyEvent.VK_ESCAPE) {
         this.close();
      }
   }

   public void onNewGame() {
      this.parentController.startNewGame();
   }
   
   public void onContinueGame() {
      this.parentController.continueGame();
   }

   public void onConfiguration() {
      this.parentController.showConfiguration();
   }

   public void onCloseGame() {
      this.close();
   }
   
   @Override
   public MenuView getView(){
      return (MenuView)super.getView();
   }

   public void setGameRunning(boolean b) {
      this.getView().setGameRunning(b);
   }
}
