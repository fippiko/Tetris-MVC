package game.helper;

import game.model.form.Form;
import game.model.form.FormBlock;
import game.model.form.FormI;
import game.model.form.FormJ;
import game.model.form.FormL;
import game.model.form.FormT;

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
}
