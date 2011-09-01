package game.helper;

import java.util.Hashtable;

public abstract class TimeHelper extends Helper {

   private static Hashtable<Object, Hashtable<String, Long>> lastMap     = new Hashtable<Object, Hashtable<String, Long>>();

   private static Hashtable<Object, Hashtable<String, Long>> deltaSumMap = new Hashtable<Object, Hashtable<String, Long>>();

   public static boolean timeReached(Object object, String method, long miliSeconds) {
      if (!deltaSumMap.containsKey(object)) {
         deltaSumMap.put(object, new Hashtable<String, Long>());
         lastMap.put(object, new Hashtable<String, Long>());
      }

      Hashtable<String, Long> allMethodsDelta = deltaSumMap.get(object);
      Hashtable<String, Long> allMethodsLast = lastMap.get(object);

      if (!allMethodsDelta.containsKey(method)) {
         allMethodsDelta.put(method, (long) 0);
      }
      else {
         long existingDelta = allMethodsDelta.get(method);
         long delta = System.nanoTime() - allMethodsLast.get(method);
         allMethodsDelta.put(method, existingDelta + delta);
      }

      allMethodsLast.put(method, System.nanoTime());

      long deltaSumInMiliSeconds = allMethodsDelta.get(method) / (1000 * 1000);

      if (deltaSumInMiliSeconds > miliSeconds) {
         allMethodsDelta.put(method, (long) 0);
         return true;
      }

      return false;
   }
}
