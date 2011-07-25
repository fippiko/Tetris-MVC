package game.enums;

import java.lang.reflect.Type;

public enum ConfigurationAttribute {
   NAME("Name", "Unkown", String.class),
   SPEED("Speed", "50", Integer.class);

   private String attributeString;
   private String defaultValue;

   ConfigurationAttribute(String attributeString, String defaultValue, Type type) {
      this.attributeString = attributeString;
      this.defaultValue    = defaultValue;
   }

   public static ConfigurationAttribute fromString(String attributeString) {
      if (attributeString != null) {
         for (ConfigurationAttribute configurationAttriubte : ConfigurationAttribute.values()) {
            if (attributeString.equalsIgnoreCase(configurationAttriubte.toString())) {
               return configurationAttriubte;
            }
         }
      }

      return null;
   }

   @Override
   public String toString() {
      return this.attributeString;
   }
   
   public String getDefaultValue(){
      return this.defaultValue;
   }
}
