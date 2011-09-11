package game.controller.handler.game;

import game.controller.GameController;
import game.model.Game;

public class GameoverHandler extends GameActionHandler {

   public GameoverHandler(GameController controller) {
      super(controller);
   }

   @Override
   public void work(Game game) {
      game.setGameover(true);
   }

}
