package game.helper;

import game.model.Configuration;

public abstract class ConfigurationHelper {
   private static Configuration configurationInstance = null;
   
   public static Configuration getInstance(){
      if(configurationInstance == null){
         configurationInstance = createConfiguration();
      }
      
      return configurationInstance;
   }
   
   public static void saveConfiguration(Configuration configuration){
      SerializationHelper.writeObjectToXml(configuration, Configuration.CONFIGURATIONFILE);
   }
   
   private static Configuration createConfiguration(){
      Configuration configuration = (Configuration)SerializationHelper.readObjectFromXml(Configuration.CONFIGURATIONFILE);
      
      if(configuration == null){
         setDefaultValues(configuration);
      }
      
      return configuration;
   }
   
   private static void setDefaultValues(Configuration configuration){
      configuration.setUsername("Unkown");
      configuration.setVerticalSpeed(50);
      configuration.setHorizontalSpeed(50);
   }
}
