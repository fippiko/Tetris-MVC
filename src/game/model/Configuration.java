package game.model;

import game.enums.ConfigurationAttribute;

import java.util.LinkedHashMap;

public abstract class Configuration {
   private static ConfigurationAttributeMap attributes  = new ConfigurationAttributeMap();

   private static Boolean                   initialized = false;

   public static void initializeConfiguration() {
      // add for each ConfigurationAttribute an empty value
      for (ConfigurationAttribute configurationAttribute : ConfigurationAttribute.values()) {
         attributes.put(configurationAttribute, "");
      }

      initialized = true;
   }

   public static LinkedHashMap<ConfigurationAttribute, String> getConfigurationAttributes() {
      return attributes;
   }

   public static String getAttributeValue(ConfigurationAttribute attribute) {
      String returnValue = "";
      if(attributes.containsKey(attribute)){
         returnValue = attributes.get(attribute);
      }
      
      return returnValue;
   }

   public static void saveConfigurationAttributes(ConfigurationAttributeMap configurationAttributes) {
      attributes = configurationAttributes;
   }
}
