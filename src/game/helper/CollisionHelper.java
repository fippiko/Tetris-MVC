package game.helper;

import game.model.FormUnit;
import game.model.Game;
import game.model.form.Form;

import java.util.ArrayList;

public class CollisionHelper {

   public static Boolean checkHorizontalCollision(final Form activeForm, final int horizontalDelta, final ArrayList<Form> otherForms) {
      int leftColumn = 0;
      int rightColumn = Game.COLCOUNT;

      Boolean formColide = false;
      for (FormUnit formUnit : activeForm.getUnits()) {

         if (formUnit.getColumn() + horizontalDelta < leftColumn) {
            formColide = true;
            break;
         }
         else if (formUnit.getColumn() + horizontalDelta > rightColumn) {
            formColide = true;
            break;
         }
      }

      return formColide;
   }

   public static Boolean checkVerticalCollision(final Form activeForm, final int verticalDelta, final ArrayList<Form> allForms) {
      ArrayList<Form> otherForms = new ArrayList<Form>(allForms) ;
      otherForms.remove(activeForm);

      int bottomRow = Game.ROWCOUNT;

      Boolean formColide = false;
      for (FormUnit formUnit : activeForm.getUnits()) {
         int nextRow = formUnit.getRow() + verticalDelta;

         if (nextRow > bottomRow) {
            formColide = true;
            break;
         }

         // collision with other forms
         if (isFormAtPosition(formUnit.getColumn(), bottomRow, otherForms)) {
            formColide = true;
            break;
         }
      }

      return formColide;
   }

   public static Boolean isFormAtPosition(final int column, final int row, final ArrayList<Form> otherForms) {

      Boolean isFormThere = false;

      for (Form form : otherForms) {

         for (FormUnit formUnit : form.getUnits()) {
            int unitColumn = formUnit.getColumn();
            int unitRow = formUnit.getRow();

            if (column == unitColumn) {
               if (row == unitRow) {
                  isFormThere = true;
                  break;
               }
            }
         }

      }

      return isFormThere;
   }
}
