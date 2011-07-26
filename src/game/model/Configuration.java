package game.model;

import game.enums.ConfigurationAttribute;

import java.io.Serializable;

public abstract class Configuration implements Serializable {
   public static final String               FILENAME   = "configuration.conf";

   private static ConfigurationAttributeMap attributes = new ConfigurationAttributeMap();

   public static void initializeConfiguration(ConfigurationAttributeMap existingAttributes) {

      if (existingAttributes == null) {
         for (ConfigurationAttribute configurationAttribute : ConfigurationAttribute.values()) {
            attributes.put(configurationAttribute, "");
         }
      }
      else {
         setConfigurationAttributes(existingAttributes);
      }
   }

   public static ConfigurationAttributeMap getConfigurationAttributes() {
      return attributes;
   }

   public static String getAttributeValue(ConfigurationAttribute attribute) {
      String returnValue = "";
      if (attributes.containsKey(attribute)) {
         returnValue = attributes.get(attribute);
      }

      return returnValue;
   }

   public static void setConfigurationAttributes(ConfigurationAttributeMap newAttributes) {
      for (ConfigurationAttribute attribute : newAttributes.keySet()) {
         attributes.put(attribute, newAttributes.get(attribute));
      }
   }
}
