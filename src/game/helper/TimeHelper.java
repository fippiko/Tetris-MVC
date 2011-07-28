package game.helper;

import game.controller.Controller;

import java.util.Hashtable;
import java.util.List;

public class TimeHelper {

   private static Hashtable<Controller, Long>                    lastMap     = new Hashtable<Controller, Long>();
   // private static Hashtable<Controller, Long> deltaSumMap = new
   // Hashtable<Controller, Long>();

   private static Hashtable<Controller, Hashtable<String, Long>> deltaSumMap = new Hashtable<Controller, Hashtable<String, Long>>();

   public static void pushTime(Controller controller) {

      if (lastMap.containsKey(controller)) {
         long last = lastMap.get(controller);
         long delta = System.nanoTime() - last;
         
         if(!deltaSumMap.containsKey(controller)){
            deltaSumMap.put(controller, new Hashtable<String, Long>());
         }

         Hashtable<String, Long> allMethods = deltaSumMap.get(controller);

         for (String method : allMethods.keySet()) {
            long existingDelta = 0;
            if (allMethods.containsKey(method)) {
               existingDelta = allMethods.get(method);
            }
            allMethods.put(method, existingDelta + delta);
         }
      }

      lastMap.put(controller, System.nanoTime());
   }

   public static Boolean timeReached(Controller controller, String method, long miliSeconds) {
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
