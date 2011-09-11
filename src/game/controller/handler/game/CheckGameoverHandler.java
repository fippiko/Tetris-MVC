package game.controller.handler.game;

import game.controller.GameController;
import game.enums.GameAction;
import game.helper.CollisionHelper;
import game.model.FormUnit;
import game.model.Game;
import game.model.form.Form;

public class CheckGameoverHandler extends GameActionHandler {

   public CheckGameoverHandler(GameController controller) {
      super(controller);
   }

   @Override
   public void work(Game game) {
      Form activeForm = game.getActiveForm();

      boolean rowZero = false;
      boolean collision = false;
      boolean gameover = false;

      for (FormUnit unit : activeForm.getUnits()) {
         if (unit.getRow() <= 0) {
            rowZero = true;
         }
      }

      collision = !CollisionHelper.checkVerticalCollision(activeForm, 0, game.getDeadForms());

      gameover = collision && rowZero;

      if (gameover) {
         game.setAction(GameAction.GAMEOVER);
      }
      else {
         game.setAction(GameAction.MOVEFORM);
      }
   }

}
