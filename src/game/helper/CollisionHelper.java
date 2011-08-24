package game.helper;

import game.model.FormUnit;
import game.model.Game;
import game.model.form.Form;

import java.util.ArrayList;

public abstract class CollisionHelper extends Helper {

   public static boolean checkCollision(FormUnit unit, int horizontalDelta, int verticalDelta, ArrayList<Form> otherForms) {
      int leftBorder = 0;
      int rightBorder = Game.COLCOUNT - 1;
      int bottomBorder = Game.ROWCOUNT - 1;

      boolean unitCollide = false;

      int nextColumn = unit.getColumn() + horizontalDelta;
      int nextRow = unit.getRow() + verticalDelta;

      // check collision with the ground
      if (nextRow > bottomBorder) {
         unitCollide = true;
      }

      // check collision with the left border
      if (nextColumn < leftBorder) {
         unitCollide = true;
      }

      // check collision with the right border
      else if (nextColumn > rightBorder) {
         unitCollide = true;
      }

      // check collision with other forms
      if (isFormAtPosition(nextColumn, nextRow, otherForms)) {
         unitCollide = true;
      }

      return !unitCollide;
   }

   public static boolean checkHorizontalCollision(final Form formToCheck, final int horizontalDelta, final ArrayList<Form> otherForms) {
      boolean formColide = false;
      for (FormUnit formUnit : formToCheck.getUnits()) {
         if (!checkCollision(formUnit, horizontalDelta, 0, otherForms)) {
            formColide = true;
            break;
         }
      }

      return !formColide;
   }

   public static boolean checkVerticalCollision(final Form formToCheck, final int verticalDelta, final ArrayList<Form> otherForms) {
      boolean formColide = false;
      for (FormUnit formUnit : formToCheck.getUnits()) {
         if (!checkCollision(formUnit, 0, verticalDelta, otherForms)) {
            formColide = true;
            break;
         }
      }

      return !formColide;
   }

   public static boolean isFormAtPosition(final int column, final int row, final ArrayList<Form> forms) {

      boolean isFormThere = false;

      for (Form form : forms) {

         for (FormUnit formUnit : form.getUnits()) {
            if (column == formUnit.getColumn()) {
               if (row == formUnit.getRow()) {
                  isFormThere = true;
                  break;
               }
            }
         }

      }

      return isFormThere;
   }

}
