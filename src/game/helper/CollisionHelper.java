package game.helper;

import java.util.ArrayList;

import game.model.Game;
import game.model.form.Form;

public class CollisionHelper {

   public static Boolean checkHorizontalCollision(final Form activeForm, final int nextColumnIndex, final ArrayList<Form> otherForms) {
      int leftColumn = 0;
      int rightColumn = Game.COLCOUNT;

      return (nextColumnIndex < leftColumn || nextColumnIndex > rightColumn);
   }

   public static Boolean checkVerticalCollision(final Form activeForm, final int nextRowIndex, final ArrayList<Form> otherForms) {
      int bottomRow = Game.ROWCOUNT;

      int bottomRowIndex = nextRowIndex + activeForm.getCurrentHeight();

      // collision with the bottom
      if (bottomRowIndex > bottomRow) {
         return true;
      }

      return false;
   }
}
