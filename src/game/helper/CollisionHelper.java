package game.helper;

import java.util.ArrayList;

import game.model.FieldMap;
import game.model.Game;
import game.model.form.Form;

public class CollisionHelper {

   public static Boolean checkHorizontalCollision(final Form activeForm, final int nextColumnIndex, final ArrayList<Form> deadForms) {
      int leftColumn = 0;
      int rightColumn = Game.COLCOUNT;

      int rightFormBorder = nextColumnIndex + activeForm.getCurrentWidth();

      return (nextColumnIndex < leftColumn || rightFormBorder > rightColumn);
   }

   public static Boolean checkVerticalCollision(final Form activeForm, final int nextRowIndex, final ArrayList<Form> deadForms) {
      int bottomRow = Game.ROWCOUNT;

      int bottomRowIndex = nextRowIndex + activeForm.getCurrentHeight();

      int formColumnIndex = activeForm.getColumnIndex();

      // collision with the bottom
      if (bottomRowIndex > bottomRow) {
         return true;
      }

      // collision with other forms
      if (isFormAtPosition(formColumnIndex, activeForm.getRowIndex() + activeForm.getCurrentHeight(), takenFields)) {
         return true;
      }

      return false;
   }

   public static Boolean isFormAtPosition(final int column, final int row, final ArrayList<Form> deadForms) {
/*
      for (Form form : otherForms) {
         if (form != activeForm) {
            int formColumn = form.getColumnIndex();
            int formWidth = form.getCurrentWidth();
            int formRow = form.getRowIndex();

            if (column >= formColumn && column <= formColumn + formWidth) {
               if (row == formRow) {
                  return true;
               }
            }
         }
      }*/

      if(takenFields.containsKey(column)){
         if(takenFields.get(column).contains(row)){
            return true;
         }
      }
      return false;
   }
}
