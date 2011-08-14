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
import java.util.Random;

public class FormHelper {

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

   public static void rotateActiveForm(Form formToRotate, ArrayList<Form> otherForms) {
      FormUnit rotateAxisUnit = formToRotate.getRotateAxisUnit();

      // cache all new FormUnit-Positions
      Hashtable<FormUnit, Integer> newColumns = new Hashtable<FormUnit, Integer>();
      Hashtable<FormUnit, Integer> newRows = new Hashtable<FormUnit, Integer>();

      // First check if some FormUnit will collide on it's new position
      Boolean formWillCollide = false;
      for (FormUnit unit : formToRotate.getUnits()) {
         if (rotateAxisUnit != null) {
            if (unit != rotateAxisUnit) {
               int columnDeltaToAxis = rotateAxisUnit.getColumn() - unit.getColumn();
               int rowDeltaToAxis = rotateAxisUnit.getRow() - unit.getRow();

               int horizontalDelta = columnDeltaToAxis + rowDeltaToAxis;
               int verticalDelta = -columnDeltaToAxis + rowDeltaToAxis;

               if (CollisionHelper.checkHorizontalCollision(unit, horizontalDelta, otherForms) && CollisionHelper.checkVerticalCollision(unit, verticalDelta, otherForms)) {
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

      // If Form will not collide, do the rotation
      if(!formWillCollide){
         for (FormUnit unit : newColumns.keySet()) {
            unit.setColumn(newColumns.get(unit));
            unit.setRow(newRows.get(unit));
         }
      }
   }
}
