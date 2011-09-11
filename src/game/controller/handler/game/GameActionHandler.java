package game.controller.handler.game;

import game.controller.GameController;
import game.controller.handler.ActionHandler;
import game.model.Game;

public abstract class GameActionHandler extends ActionHandler {

   public GameActionHandler(GameController controller) {
      super(controller);
   }

   @Override
   protected GameController getController() {
      return (GameController) super.getController();
   }

   public abstract void work(Game game);
}
