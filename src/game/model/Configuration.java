package game.model;

import game.helper.SerializationHelper;
import game.model.Configuration;

import java.lang.reflect.Array;
import java.lang.reflect.Type;

public enum Configuration {
   NAME("Name", "Unkown", String.class),
   SPEED("Speed", "50", Integer.class),
   DIFICULTY("Dificulty", "Medium", new String[]{"Easy", "Medium", "Hard"}),
   FILENAME("Filename", "configuration.txt", String.class),
   VERTICALSPEED("Vertical Speed in ms", "200", Integer.class);

   private String   attributeString;
   private String   defaultValue;
   private String[] options;
   private Type     type;

   private static ConfigurationAttributeMap attributes = new ConfigurationAttributeMap();

   Configuration(String attributeString, String defaultValue, Type type) {
      this.attributeString = attributeString;
      this.defaultValue = defaultValue;
      this.type = type;
   }

   Configuration(String attributeString, String defaultValue, String[] options) {
      this.attributeString = attributeString;
      this.defaultValue = defaultValue;
      this.options = options;
      this.type = Array.class;
   }
   
   public String getValue(){
      return getAttributeValue(this);
   }

   public static Configuration fromString(String attributeString) {
      if (attributeString != null) {
         for (Configuration configurationAttriubte : Configuration.values()) {
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

   public String getDefaultValue() {
      return this.defaultValue;
   }

   public String[] getOptions() {
      return this.options;
   }

   public Type getType() {
      return this.type;
   }
    

   public static ConfigurationAttributeMap getConfigurationAttributes() {
      return attributes;
   }

   public static String getAttributeValue(Configuration attribute) {
      String returnValue = "";
      if (attributes.containsKey(attribute)) {
         returnValue = attributes.get(attribute);
      }

      return returnValue;
   }

   public static void setConfigurationAttributes(ConfigurationAttributeMap newAttributes) {
      for (Configuration attribute : newAttributes.keySet()) {
         attributes.put(attribute, newAttributes.get(attribute));
      }
   }

   public static void initializeConfiguration() {
      ConfigurationAttributeMap newAttributes = null;
      newAttributes = (ConfigurationAttributeMap) SerializationHelper.readObjectFromXml(Configuration.FILENAME.getValue());
      initializeConfiguration(newAttributes);
   }
   
   public static void initializeConfiguration(ConfigurationAttributeMap existingAttributes) {
      if (existingAttributes == null) {
         for (Configuration configurationAttribute : Configuration.values()) {
            attributes.put(configurationAttribute, configurationAttribute.getDefaultValue());
         }
      }
      else {
         setConfigurationAttributes(existingAttributes);
      }
   }
}
