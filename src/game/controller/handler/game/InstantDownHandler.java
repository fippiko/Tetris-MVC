package game.controller.handler.game;

import game.controller.GameController;
import game.enums.GameAction;
import game.helper.FormHelper;
import game.model.Game;
import game.model.form.Form;

public class InstantDownHandler extends GameActionHandler {

   public InstantDownHandler(GameController controller) {
      super(controller);
   }

   @Override
   public void work(Game game) {
      Form activeForm = game.getActiveForm();

      boolean formIsAtBottom = false;
      while (!formIsAtBottom) {
         formIsAtBottom = !FormHelper.moveFormVertical(activeForm, 1, game.getDeadForms());
      }

      game.setAction(GameAction.BREAKDOWN);
   }

}
