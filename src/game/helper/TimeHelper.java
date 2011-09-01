package game.helper;

import game.controller.Controller;

import java.util.Hashtable;

public abstract class TimeHelper extends Helper {

   private static Hashtable<Object, Long>                    lastMap     = new Hashtable<Object, Long>();

   private static Hashtable<Object, Hashtable<String, Long>> deltaSumMap = new Hashtable<Object, Hashtable<String, Long>>();

   public static void pushTime(Object object) {

      if (lastMap.containsKey(object)) {
         long last = lastMap.get(object);
         long delta = System.nanoTime() - last;

         if (!deltaSumMap.containsKey(object)) {
            deltaSumMap.put(object, new Hashtable<String, Long>());
         }

         Hashtable<String, Long> allMethods = deltaSumMap.get(object);

         for (String method : allMethods.keySet()) {
            pushTime(object, method, delta);
         }
      }

      lastMap.put(object, System.nanoTime());
   }

   public static void pushTime(Object object, String method) {
      long last = lastMap.get(object);
      long delta = System.nanoTime() - last;

      pushTime(object, method, delta);
   }

   private static void pushTime(Object object, String method, long delta) {
      Hashtable<String, Long> allMethods = deltaSumMap.get(object);

      long existingDelta = 0;
      if (allMethods.containsKey(method)) {
         existingDelta = allMethods.get(method);
      }
      allMethods.put(method, existingDelta + delta);
   }
   
   public static void resetTime(Object object, String method){
      if (deltaSumMap.containsKey(object)) {
         Hashtable<String, Long> allMethods = deltaSumMap.get(object);

         if (allMethods.containsKey(method)) {
            allMethods.put(method, (long) 0);
         }
      }else{
         pushTime(object, method, (long)0);
      }
   }

   public static boolean timeReached(Object object, String method, long miliSeconds) {
      if (deltaSumMap.containsKey(object)) {
         Hashtable<String, Long> allMethods = deltaSumMap.get(object);

         if (!allMethods.containsKey(method)) {
            allMethods.put(method, (long) 0);
         }

         long deltaSumInMiliSeconds = allMethods.get(method) / (1000 * 1000);

         if (deltaSumInMiliSeconds > miliSeconds) {
            allMethods.remove(method);
            return true;
         }
      }

      return false;
   }
}
