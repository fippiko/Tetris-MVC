package game.controller.handler.game;

import game.controller.GameController;
import game.enums.GameAction;
import game.enums.Sound;
import game.helper.FormHelper;
import game.helper.LevelHelper;
import game.helper.ScoreHelper;
import game.helper.SoundHelper;
import game.helper.TimeHelper;
import game.model.FormUnitRow;
import game.model.Game;

import java.util.ArrayList;
import java.util.Collections;

public class BreakdownHandler extends GameActionHandler {
   private final int blinks       = 3;
   private int       blinkCounter = 0;
   private boolean   soundPlayed  = false;

   public BreakdownHandler(GameController controller) {
      super(controller);
   }

   @Override
   public void work(Game game) {
      boolean blinking = false;

      // get the filled rows and a arraylist of the indizies
      ArrayList<FormUnitRow> filledRows = FormHelper.getFilledRows(game.getAllForms());
      ArrayList<Integer> filledRowIndizes = new ArrayList<Integer>();

      for (FormUnitRow row : filledRows) {
         filledRowIndizes.add(row.getRow());
      }
      Collections.sort(filledRowIndizes);

      // if there's at least one filled row, start the breakdown
      if (filledRows.size() > 0) {
         if (!this.soundPlayed) {
            SoundHelper.playSound(Sound.BREAKDOWN, false);
            this.soundPlayed = true;
         }

         // but first let the formunits blink as much as said
         if (this.blinkCounter != this.blinks * 2) {
            blinking = true;

            if (TimeHelper.timeReached(this, "blinking", 200)) {
               for (FormUnitRow row : filledRows) {
                  row.setBlinkState((blinkCounter % 2) == 0);
               }

               if (this.blinkCounter == 0) {
                  SoundHelper.playSound(Sound.BREAKDOWN, false);
               }

               this.blinkCounter++;
            }
         }

         // if the blinking is completed, we can start the breakdown
         if (!blinking) {
            this.blinkCounter = 0;

            for (FormUnitRow filledRow : filledRows) {
               FormHelper.removeAllUnitsOnRow(game.getAllForms(), filledRow);
            }

            int highestRow = filledRowIndizes.get(filledRowIndizes.size() - 1);
            for (int rowIndex = highestRow; rowIndex >= 0; rowIndex--) {
               FormHelper.moveAllUnitsOnRowDown(game.getAllForms(), rowIndex, filledRows.size());
            }

            game.addClearedRows(filledRows.size());
            game.setLevel(LevelHelper.getCurrentLevel(game.getClearedRows()));
            game.setScore(ScoreHelper.getCurrentScore(game.getClearedRows()));
         }
      }

      // only add a new form if the blinking is completed
      if (!blinking) {
         game.setAction(GameAction.NEWFORM);
         this.soundPlayed = false;
      }
   }

}
