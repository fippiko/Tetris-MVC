package game.helper;

import java.util.Hashtable;
import java.util.Map;
import java.util.Random;

import game.model.form.Form;
import game.model.form.FormBlock;
import game.model.form.FormL;

public class FormHelper {

   public static Form generateRandomForm(int startcol, int startRow) {
      Random random = new Random();
      
      Form randomForm = null;
      
      switch(random.nextInt(2)){
         case 0:
            randomForm = new FormBlock(startcol, startRow);
            break;
         case 1:
            randomForm = new FormL(startcol, startRow);
            break;
      }
      
      return randomForm;
   }
   
   /*
   public static int calculateNextColumnIndex(Form activeForm) {
      int currentColumnIndex = activeForm.getColumnIndex();

      if (activeForm.getHorizontalSpeed() < 0) {
         return currentColumnIndex - 1;
      }
      else if (activeForm.getHorizontalSpeed() > 0) {
         return currentColumnIndex + 1;
      }

      return currentColumnIndex;
   }

   public static int calculateNextRowIndex(Form activeForm) {
      int currentRowIndex = activeForm.getRowIndex();
      int newRowIndex = currentRowIndex;

      if (activeForm.getVerticalSpeed() > 0) {
         newRowIndex = currentRowIndex + 1;
      }

      return newRowIndex;
   }
   */
/*
   public static Hashtable<Integer, Integer> generateFieldMap(Form form) {
      Hashtable<Integer, Integer> fieldMap = new Hashtable<Integer, Integer>();

      int currentColumnIndex = form.getColumnIndex();
      int currentRowIndex = form.getRowIndex();

      for (int column = currentColumnIndex; column < currentColumnIndex + form.getCurrentWidth(); column++) {
         for (int row = currentRowIndex; row < currentRowIndex + form.getCurrentHeight(); row++) {
            fieldMap.put(column, row);
         }
      }
      
      return fieldMap;
   }
   */
}
