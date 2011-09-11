package game.controller.handler.game;

import game.controller.GameController;
import game.model.Game;

public class PauseGameHandler extends GameActionHandler {

   public PauseGameHandler(GameController controller) {
      super(controller);
   }

   @Override
   public void work(Game game) {
      game.setPaused(true);

      // what to do in a pause? correct! nothing...
   }

}
