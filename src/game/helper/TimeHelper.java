package game.helper;

public class TimeHelper {
   private static long deltaSum = 0;
   private static long delta = 0;
   private static long last = 0;
   
   public static long computeDelta()
   {
      delta = System.nanoTime() - last;
      last = System.nanoTime();
      deltaSum += delta;
      
      return delta;
   }
}
