package game.controller.handler.game;

import game.controller.GameController;
import game.enums.GameAction;
import game.helper.FormHelper;
import game.model.Game;
import game.model.form.Form;

public class NewFormHandler extends GameActionHandler {

   public NewFormHandler(GameController controller) {
      super(controller);
   }

   @Override
   public void work(Game game) {
      int startcol = game.getFormStartColumnIndex();

      // get the first form from the list and remove it from it
      Form nextForm = game.pollNextForm();

      // add the first form in the list to the game
      game.addForm(nextForm);

      // now add a new element to the end
      game.addNextForm(FormHelper.generateRandomForm(startcol, 0));

      game.setAction(GameAction.CHECKGAMEOVER);
   }

}
