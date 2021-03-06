package game.controller;

import game.helper.InputHelper;
import game.view.MenuView;

import java.awt.event.KeyEvent;

public class MenuController extends Controller {

   public MenuController(MainController parentController) {
      super(parentController);
   }

   @Override
   protected boolean initialize() {
      this.setView(new MenuView(this));

      return true;
   }

   @Override
   public MainController getParentController() {
      return (MainController) super.getParentController();
   }

   public void onNewGame() {
      this.getParentController().startNewGame();
   }

   public void onContinueGame() {
      this.getParentController().continueGame();
   }

   public void onConfiguration() {
      this.getParentController().showConfiguration();
   }

   public void onCloseGame() {
      this.close();
   }

   @Override
   public MenuView getView() {
      return (MenuView) super.getView();
   }

   public void setGameRunning(boolean b) {
      this.getView().setGameRunning(b);
   }

   @Override
   public void updateView() {

   }

   @Override
   protected void handleInput() {

      if (InputHelper.isKeyPressed(KeyEvent.VK_ESCAPE)) {
         this.close();
      }

   }
}
