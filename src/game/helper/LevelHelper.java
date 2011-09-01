package game.helper;

import game.enums.Level;

public class LevelHelper extends Helper {

   public static int getCurrentLevel(int rows) {

      int levelCounter = 0;
      for (Level level : Level.values()) {
         levelCounter++;

         if (level.getNeededRows() > rows) {
            break;
         }
      }

      return levelCounter;
   }
}
