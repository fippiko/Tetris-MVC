package game.helper;

import game.enums.Level;

public class LevelHelper extends Helper {

   public static Level getCurrentLevel(int rows) {

      Level searchedLevel = null;
      for (Level level : Level.values()) {

         if (level.getNeededRows() > rows) {
            searchedLevel = level;
            break;
         }
      }

      return searchedLevel;
   }
}
