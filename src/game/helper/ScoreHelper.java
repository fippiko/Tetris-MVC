package game.helper;

public class ScoreHelper extends Helper {

   public static int getCurrentScore(int clearedRows) {
      return clearedRows * 25;
   }

}
