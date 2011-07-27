package game.helper;

import game.model.form.Form;

public class FormHelper {

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

      if (activeForm.getVerticalSpeed() < 0) {
         return currentRowIndex - 1;
      }
      else if (activeForm.getVerticalSpeed() > 0) {
         return currentRowIndex + 1;
      }

      return currentRowIndex;
   }

}
