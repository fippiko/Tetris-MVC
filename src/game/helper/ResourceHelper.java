package game.helper;

import game.enums.Resources;

import java.util.Locale;
import java.util.ResourceBundle;

public abstract class ResourceHelper extends Helper {
   // private static Locale[] supportedLocales = {Locale.GERMAN,
   // Locale.ENGLISH};

   private static ResourceBundle currentResources;

   private static Locale         currentLocale = Locale.ENGLISH;

   private static ResourceBundle getResources() {
      if (currentResources == null) {
         currentResources = ResourceBundle.getBundle("Resources", currentLocale);
      }

      return currentResources;
   }

   public static String getString(Resources key) {
      String keyAsString = key.toString();
      String returnString = key.toString();

      if (getResources().containsKey(keyAsString)) {
         returnString = getResources().getString(keyAsString);
      }

      return returnString;
   }
}
