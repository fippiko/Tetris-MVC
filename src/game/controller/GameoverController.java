package game.controller;

import game.view.game.GameoverView;

import java.awt.event.KeyEvent;

public class GameoverController extends ControllerBase {
   public GameoverController(ControllerBase parentController) {
      super(parentController);
   }

   @Override
   protected boolean initialize() {
      this.setView(new GameoverView(this));

      return true;
   }

   @Override
   public GameoverView getView() {
      return (GameoverView) super.getView();
   }

   @Override
   public GameController getParentController() {
      return (GameController) super.getParentController();
   }

   @Override
   public boolean handleKey(KeyEvent keyEvent) {
      super.handleKey(keyEvent);

      boolean handled = true;

      int keyCode = keyEvent.getKeyCode();
      switch (keyCode) {
         case KeyEvent.VK_SPACE :
            this.getParentController().startNewGame();
            break;

         default :
            handled = false;
            break;
      }

      return handled;
   }
}
