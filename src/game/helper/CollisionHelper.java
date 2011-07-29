package game.helper;

import java.util.ArrayList;

import game.model.Game;
import game.model.form.Form;

public class CollisionHelper {

   public static Boolean checkHorizontalCollision(final Form activeForm, final ArrayList<Form> otherForms) {
      int leftColumn = 0;
      int rightColumn = Game.COLCOUNT;

      int nextFormColumnIndex = FormHelper.calculateNextColumnIndex(activeForm);

      return (nextFormColumnIndex < leftColumn || nextFormColumnIndex > rightColumn);
   }

   public static Boolean checkVerticalCollision(final Form activeForm, final ArrayList<Form> otherForms) {
      int bottomRow = Game.ROWCOUNT;

      int nextFormRowIndex = FormHelper.calculateNextRowIndex(activeForm);
      int bottomRowIndex = nextFormRowIndex + activeForm.getCurrentHeight();

      //collision with the bottom
      if(bottomRowIndex > bottomRow){
         return true;
      }
      
      return false;
   }
}
