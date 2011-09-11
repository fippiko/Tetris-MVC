package game.controller.handler.game;

import game.controller.GameController;
import game.enums.GameAction;
import game.helper.FormHelper;
import game.model.Game;
import game.model.form.Form;

import java.util.ArrayList;

public class RotateFormHandler extends GameActionHandler {

   public RotateFormHandler(GameController controller) {
      super(controller);
   }

   @Override
   public void work(Game game) {
      Form activeForm = game.getActiveForm();
      ArrayList<Form> otherForms = game.getDeadForms();

      FormHelper.rotateForm(activeForm, otherForms);

      game.setAction(GameAction.MOVEFORM);
   }

}
