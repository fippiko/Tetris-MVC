package game.helper;

import game.model.FormUnit;
import game.model.Game;
import game.model.form.Form;

import java.util.ArrayList;

public abstract class CollisionHelper extends Helper{


   public static boolean checkCollision(FormUnit unit, int horizontalDelta, int verticalDelta, ArrayList<Form> otherForms) {
      int leftBorder = 0;
      int rightBorder = Game.COLCOUNT - 1;
      int bottomBorder = Game.ROWCOUNT - 1;
      
      Boolean unitCollide = false;
      
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
   
   public static Boolean checkHorizontalCollision(final Form formToCheck, final int horizontalDelta, final ArrayList<Form> otherForms) {
      Boolean formColide = false;
      for (FormUnit formUnit : formToCheck.getUnits()) {
         if(!checkCollision(formUnit, horizontalDelta, 0, otherForms)){
            formColide = true;
            break;
         }
      }

      return !formColide;
   }
   
   
   public static Boolean checkVerticalCollision(final Form formToCheck, final int verticalDelta, final ArrayList<Form> otherForms) {
      Boolean formColide = false;
      for (FormUnit formUnit : formToCheck.getUnits()) {
         if(!checkCollision(formUnit, 0, verticalDelta, otherForms)){
            formColide = true;
            break;
         }
      }

      return !formColide;
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
