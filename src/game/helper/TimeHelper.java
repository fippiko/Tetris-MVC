package game.helper;

import game.controller.ControllerBase;

import java.util.Hashtable;

public abstract class TimeHelper extends Helper {

   private static Hashtable<ControllerBase, Long>                    lastMap     = new Hashtable<ControllerBase, Long>();

   private static Hashtable<ControllerBase, Hashtable<String, Long>> deltaSumMap = new Hashtable<ControllerBase, Hashtable<String, Long>>();

   public static void pushTime(ControllerBase controller) {

      if (lastMap.containsKey(controller)) {
         long last = lastMap.get(controller);
         long delta = System.nanoTime() - last;

         if (!deltaSumMap.containsKey(controller)) {
            deltaSumMap.put(controller, new Hashtable<String, Long>());
         }

         Hashtable<String, Long> allMethods = deltaSumMap.get(controller);

         for (String method : allMethods.keySet()) {
            pushTime(controller, method, delta);
         }
      }

      lastMap.put(controller, System.nanoTime());
   }

   public static void pushTime(ControllerBase controller, String method) {
      long last = lastMap.get(controller);
      long delta = System.nanoTime() - last;

      pushTime(controller, method, delta);
   }

   private static void pushTime(ControllerBase controller, String method, long delta) {
      Hashtable<String, Long> allMethods = deltaSumMap.get(controller);

      long existingDelta = 0;
      if (allMethods.containsKey(method)) {
         existingDelta = allMethods.get(method);
      }
      allMethods.put(method, existingDelta + delta);
   }

   public static boolean timeReached(ControllerBase controller, String method, long miliSeconds) {
      if (deltaSumMap.containsKey(controller)) {
         Hashtable<String, Long> allMethods = deltaSumMap.get(controller);

         if (!allMethods.containsKey(method)) {
            allMethods.put(method, (long) 0);
         }

         long deltaSumInMiliSeconds = allMethods.get(method) / (1000 * 1000);

         if (deltaSumInMiliSeconds > miliSeconds) {
            allMethods.put(method, (long) 0);
            return true;
         }
      }

      return false;
   }
}
