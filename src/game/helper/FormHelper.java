package game.helper;

import game.model.FormUnit;
import game.model.form.Form;
import game.model.form.FormBlock;
import game.model.form.FormI;
import game.model.form.FormJ;
import game.model.form.FormL;
import game.model.form.FormT;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

public abstract class FormHelper {

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

      // chache all calculated new positions of the units, to avoid calculating it twice
      Hashtable<FormUnit, Integer> newColumns = new Hashtable<FormUnit, Integer>();
      Hashtable<FormUnit, Integer> newRows = new Hashtable<FormUnit, Integer>();

      // go through each unit of the form and check if it will collide
      Boolean formWillCollide = false;
      for (FormUnit unit : formToRotate.getUnits()) {
         if (rotateAxisUnit != null) {
            if (unit != rotateAxisUnit) {
               int horizontalDeltaToAxis = rotateAxisUnit.getColumn() - unit.getColumn();
               int verticalDeltaToAxis = rotateAxisUnit.getRow() - unit.getRow();

               int horizontalDelta = horizontalDeltaToAxis + verticalDeltaToAxis;
               int verticalDelta = -horizontalDeltaToAxis + verticalDeltaToAxis;

               if (CollisionHelper.checkCollision(unit, horizontalDelta, verticalDelta,otherForms)) {
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
      if(!formWillCollide){
         //get for each unit the new position from the temporary cache
         for (FormUnit unit : newColumns.keySet()) {
            unit.setColumn(newColumns.get(unit));
            unit.setRow(newRows.get(unit));
         }
      }
   }
}
