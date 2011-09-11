package game.controller.handler.game;

import game.controller.GameController;
import game.enums.GameAction;
import game.helper.FormHelper;
import game.model.Game;
import game.model.form.Form;

public class NewGameHandler extends GameActionHandler {

   public NewGameHandler(GameController controller) {
      super(controller);
   }

   @Override
   public void work(Game game) {
      this.getController().initialize();
      game = this.getController().getGame();

      Form firstForm = FormHelper.generateRandomForm(game.getFormStartColumnIndex(), 0);

      game.addNextForm(firstForm);

      game.setAction(GameAction.NEWFORM);
   }

}
