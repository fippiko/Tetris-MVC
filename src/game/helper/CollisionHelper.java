package game.helper;

import game.model.FormUnit;
import game.model.Game;
import game.model.form.Form;

import java.util.ArrayList;

public class CollisionHelper {

   public static Boolean checkHorizontalCollision(final FormUnit unitToCheck, final int horizontalDelta, final ArrayList<Form> otherForms) {
      int leftBorder = 0;
      int rightBorder = Game.COLCOUNT - 1;

      Boolean unitColide = false;
      int nextColumn = unitToCheck.getColumn() + horizontalDelta;

      // check collision with the left border
      if (nextColumn < leftBorder) {
         unitColide = true;
      }

      // check collision with the right border
      else if (nextColumn > rightBorder) {
         unitColide = true;
      }

      // check collision with other forms
      if (isFormAtPosition(nextColumn, unitToCheck.getRow(), otherForms)) {
         unitColide = true;
      }

      return !unitColide;
   }
   
   public static Boolean checkHorizontalCollision(final Form formToCheck, final int horizontalDelta, final ArrayList<Form> otherForms) {
      Boolean formColide = false;
      for (FormUnit formUnit : formToCheck.getUnits()) {
         if(!checkHorizontalCollision(formUnit, horizontalDelta, otherForms)){
            formColide = true;
            break;
         }
      }

      return !formColide;
   }
   
   public static Boolean checkVerticalCollision(final FormUnit unitToCheck, final int verticalDelta, final ArrayList<Form> otherForms) {
      int bottomRow = Game.ROWCOUNT - 1;

      Boolean unitColide = false;

      int nextRow = unitToCheck.getRow() + verticalDelta;

      // check collision with the ground
      if (nextRow > bottomRow) {
         unitColide = true;
      }

      // check collision with other forms
      if (isFormAtPosition(unitToCheck.getColumn(), nextRow, otherForms)) {
         unitColide = true;
      }
      

      return !unitColide;
   }
   
   public static Boolean checkVerticalCollision(final Form formToCheck, final int verticalDelta, final ArrayList<Form> otherForms) {
      Boolean formColide = false;
      for (FormUnit formUnit : formToCheck.getUnits()) {
         if(!checkVerticalCollision(formUnit, verticalDelta, otherForms)){
            formColide = true;
            break;
         }
      }

      return !formColide;
   }

   private static Boolean isFormAtPosition(final int column, final int row, final ArrayList<Form> forms) {

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
