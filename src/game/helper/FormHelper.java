package game.helper;

import game.model.FormUnit;
import game.model.Game;
import game.model.form.Form;
import game.model.form.FormBlock;
import game.model.form.FormI;
import game.model.form.FormJ;
import game.model.form.FormL;
import game.model.form.FormT;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Random;

/**
 * @author philipp
 * 
 */
public abstract class FormHelper extends Helper {

   public static ArrayList<Integer> getFilledRows(ArrayList<Form> allForms) {
      ArrayList<Integer> filledRows = new ArrayList<Integer>();

      for (int rowIndex = Game.ROWCOUNT; rowIndex >= 0; rowIndex--) {
         boolean rowFilled = true;
         for (int columnIndex = 0; columnIndex < Game.COLCOUNT; columnIndex++) {
            if (!CollisionHelper.isFormAtPosition(columnIndex, rowIndex, allForms)) {
               rowFilled = false;
               break;
            }
         }

         if (rowFilled) {
            filledRows.add(rowIndex);
         }
      }

      return filledRows;
   }

   public static void removeAllUnitsOnRow(final ArrayList<Form> allForms, int rowIndex) {
      for (Form form : allForms) {
         Iterator<FormUnit> unitIterator = form.getUnits().iterator();

         while (unitIterator.hasNext()) {
            FormUnit unit = unitIterator.next();

            if (unit.getRow() == rowIndex) {
               unitIterator.remove();
            }
         }
      }
   }

   public static void moveAllUnitsOnRowDown(final ArrayList<Form> allForms, final int rowIndex, final int rowDelta) {
      for (Form form : allForms) {
         for (FormUnit unit : form.getUnits()) {
            if (unit.getRow() == rowIndex) {
               unit.setRow(unit.getRow() + rowDelta);
            }
         }
      }
   }

   public static boolean moveFormHorizontal(Form formToMove, int horizontalDelta, ArrayList<Form> otherForms) {
      // check if there will be a collision on the new column
      boolean collided = !CollisionHelper.checkHorizontalCollision(formToMove, horizontalDelta, otherForms);

      if (!collided && horizontalDelta != 0) {
         for (FormUnit unit : formToMove.getUnits()) {
            int currentColumn = unit.getColumn();
            int newColumn = currentColumn + horizontalDelta;

            unit.setColumn(newColumn);
         }
      }

      return !collided;
   }

   /**
    * Moves the given form vertical if possible (no collision)
    * 
    * @param formToMove
    *        The form to move
    * @param verticalDelta
    *        The columns to move (e.g. left: -1, right: 1)
    * @param otherForms
    *        All other forms, needed to check the collision with them
    * @return true if succesfully moved
    */
   public static boolean moveFormVertical(Form formToMove, int verticalDelta, ArrayList<Form> otherForms) {
      // check if there will be a collision on the new row
      boolean collided = !CollisionHelper.checkVerticalCollision(formToMove, verticalDelta, otherForms);

      if (!collided) {
         for (FormUnit unit : formToMove.getUnits()) {
            int currentRow = unit.getRow();
            int newRow = currentRow + verticalDelta;

            unit.setRow(newRow);
         }
      }

      return !collided;
   }

   public static void rotateForm(Form formToRotate, ArrayList<Form> otherForms) {
      FormUnit rotateAxisUnit = formToRotate.getRotateAxisUnit();

      // chache all calculated new positions of the units, to avoid calculating
      // it twice
      Hashtable<FormUnit, Integer> newColumns = new Hashtable<FormUnit, Integer>();
      Hashtable<FormUnit, Integer> newRows = new Hashtable<FormUnit, Integer>();

      // go through each unit of the form and check if it will collide
      boolean formWillCollide = false;
      for (FormUnit unit : formToRotate.getUnits()) {
         if (rotateAxisUnit != null) {
            if (unit != rotateAxisUnit) {
               int horizontalDeltaToAxis = rotateAxisUnit.getColumn() - unit.getColumn();
               int verticalDeltaToAxis = rotateAxisUnit.getRow() - unit.getRow();

               int horizontalDelta = horizontalDeltaToAxis + verticalDeltaToAxis;
               int verticalDelta = -horizontalDeltaToAxis + verticalDeltaToAxis;

               if (CollisionHelper.checkCollision(unit, horizontalDelta, verticalDelta, otherForms)) {
                  // if the unit doesn't collide, add it to the temporary cache
                  newColumns.put(unit, unit.getColumn() + horizontalDelta);
                  newRows.put(unit, unit.getRow() + verticalDelta);
               }
               else {
                  formWillCollide = true;
                  break;
               }

            }
         }
      }

      // if the form won't collide, do the rotation
      if (!formWillCollide) {
         // get for each unit the new position from the temporary cache
         for (FormUnit unit : newColumns.keySet()) {
            unit.setColumn(newColumns.get(unit));
            unit.setRow(newRows.get(unit));
         }
      }
   }

   public static Form generateRandomForm(int startcol, int startRow) {
      Random random = new Random();

      Form randomForm = null;
      switch (random.nextInt(5)) {
         case 0 :
            randomForm = new FormBlock(startcol, startRow);
            break;
         case 1 :
            randomForm = new FormL(startcol, startRow);
            break;
         case 2 :
            randomForm = new FormI(startcol, startRow);
            break;
         case 3 :
            randomForm = new FormT(startcol, startRow);
            break;
         case 4 :
            randomForm = new FormJ(startcol, startRow);
            break;
      }

      return randomForm;
   }
}
