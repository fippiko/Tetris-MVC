package game.controller.handler.game;

import game.controller.GameController;
import game.enums.GameAction;
import game.helper.FormHelper;
import game.helper.MovementHelper;
import game.helper.TimeHelper;
import game.model.Game;
import game.model.form.Form;

public class MovementHandler extends GameActionHandler {

   public MovementHandler(GameController controller) {
      super(controller);
   }

   @Override
   public void work(Game game) {
      Form activeForm = game.getActiveForm();
      boolean verticalCollided = false;

      // do horizontal movement on horizontal-interval
      if (TimeHelper.timeReached(this, "moveHorizontal", MovementHelper.getHorizontalSpeedInterval())) {
         int horizontalDelta = MovementHelper.getHorizontalDelta();
         FormHelper.moveFormHorizontal(activeForm, horizontalDelta, game.getDeadForms());
      }

      // do vertical movement on vertical-interval
      if (TimeHelper.timeReached(this, "moveVertical", MovementHelper.getVerticalSpeedInterval(game.getLevel()))) {
         int verticalDelta = MovementHelper.getVerticalDelta();
         verticalCollided = !FormHelper.moveFormVertical(activeForm, verticalDelta, game.getDeadForms());
      }

      if (verticalCollided) {
         game.setAction(GameAction.CHECKBREAKDOWN);
      }
   }
}
