package game.controller.handler.game;

import game.controller.GameController;
import game.enums.GameAction;
import game.helper.CollisionHelper;
import game.helper.MovementHelper;
import game.helper.TimeHelper;
import game.model.Game;
import game.model.form.Form;

public class CheckBreakdownHandler extends GameActionHandler {

   public CheckBreakdownHandler(GameController controller) {
      super(controller);
   }

   @Override
   public void work(Game game) {
      boolean verticalCollided = false;
      boolean lastChanceUsed = false;

      Form activeForm = game.getActiveForm();

      verticalCollided = !CollisionHelper.checkVerticalCollision(activeForm, 1, game.getDeadForms());

      if (verticalCollided) {
         lastChanceUsed = TimeHelper.timeReached(this, "checkForBreakdown", MovementHelper.getVerticalSpeedInterval(game.getLevel()));
      }

      if (verticalCollided && lastChanceUsed) {
         game.setAction(GameAction.BREAKDOWN);
      }
   }

}
