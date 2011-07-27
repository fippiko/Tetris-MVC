package game.helper;

import game.controller.Controller;

import java.util.Hashtable;

public class TimeHelper {

   private static Hashtable<Controller, Long> lastMap = new Hashtable<Controller, Long>();
   private static Hashtable<Controller, Long> deltaSumMap = new Hashtable<Controller, Long>();
   
   public static void pushTime(Controller controller) {
      
      if(lastMap.containsKey(controller)){
         long last = lastMap.get(controller);
         long delta = System.nanoTime() - last;
         
         if(deltaSumMap.containsKey(controller)){
            long existingDeltaSum = deltaSumMap.get(controller);
            deltaSumMap.put(controller, existingDeltaSum + delta);
         }else{
            deltaSumMap.put(controller, delta);
         }
      }
      
      lastMap.put(controller, System.nanoTime());
   }

   public static Boolean timeReached(Controller controller, long miliSeconds, Boolean clearSumIfReached) {
      if(deltaSumMap.containsKey(controller)){
         long deltaSumInMiliSeconds = deltaSumMap.get(controller) / (1000 * 1000);
         if(deltaSumInMiliSeconds > miliSeconds){
            
            if(clearSumIfReached){
               deltaSumMap.put(controller, (long)0);
            }
            
            return true;
         }
      }
      
      return false;
   }
}
