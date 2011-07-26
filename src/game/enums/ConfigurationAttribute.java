package game.enums;

import java.lang.reflect.Array;
import java.lang.reflect.Type;

public enum ConfigurationAttribute {
   NAME("Name", "Unkown", String.class),
   SPEED("Speed", "50", Integer.class),
   DIFICULTY("Dificulty", "Medium", new String[] {"Easy", "Medium", "Hard"});

   private String attributeString;
   private String defaultValue;
   private String[] options;
   private Type type;
   
   ConfigurationAttribute(String attributeString, String defaultValue, Type type) {
      this.attributeString = attributeString;
      this.defaultValue    = defaultValue;
      this.type = type;
   }
   
   ConfigurationAttribute(String attributeString, String defaultValue, String[] options) {
      this.attributeString = attributeString;
      this.defaultValue    = defaultValue;
      this.options 		   = options;
      this.type 		   = Array.class;
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
   
   public String[] getOptions(){
      return this.options;
   }
   
   public Type getType(){
	   return this.type;
   }
}
