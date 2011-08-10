package game.helper;

import game.model.FormUnit;
import game.model.Game;
import game.model.form.Form;

import java.util.ArrayList;

public class CollisionHelper {

   public static Boolean checkHorizontalCollision(final Form activeForm, final int horizontalDelta, final ArrayList<Form> allForms) {
      ArrayList<Form> otherForms = new ArrayList<Form>(allForms);
      otherForms.remove(activeForm);

      int leftBorder = 0;
      int rightBorder = Game.COLCOUNT - 1;

      Boolean formColide = false;
      for (FormUnit formUnit : activeForm.getUnits()) {
         int nextColumn = formUnit.getColumn() + horizontalDelta;

         // check collision with the left border
         if (nextColumn < leftBorder) {
            formColide = true;
            break;
         }

         // check collision with the right border
         else if (nextColumn > rightBorder) {
            formColide = true;
            break;
         }

         // check collision with other forms
         if (isFormAtPosition(nextColumn, formUnit.getRow(), otherForms)) {
            formColide = true;
            break;
         }
      }

      return formColide;
   }

   public static Boolean checkVerticalCollision(final Form activeForm, final int verticalDelta, final ArrayList<Form> allForms) {
      ArrayList<Form> otherForms = new ArrayList<Form>(allForms);
      otherForms.remove(activeForm);

      int bottomRow = Game.ROWCOUNT - 1;

      Boolean formColide = false;
      for (FormUnit formUnit : activeForm.getUnits()) {
         int nextRow = formUnit.getRow() + verticalDelta;

         // check collision with the ground
         if (nextRow > bottomRow) {
            formColide = true;
            break;
         }

         // check collision with other forms
         if (isFormAtPosition(formUnit.getColumn(), nextRow, otherForms)) {
            formColide = true;
            break;
         }
      }

      return formColide;
   }

   public static Boolean isFormAtPosition(final int column, final int row, final ArrayList<Form> forms) {

      Boolean isFormThere = false;

      for (Form form : forms) {

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
