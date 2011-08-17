package game.helper;

import game.model.Configuration;

public abstract class ConfigurationHelper extends Helper {
   private static Configuration configurationInstance = null;
   
   
   public static Configuration getConfiguration(){
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
         configuration = initNewConfiguration();
      }
      
      return configuration;
   }
   
   private static Configuration initNewConfiguration(){
      Configuration configuration = new Configuration();
      
      configuration.setUsername("Unkown");
      configuration.setVerticalSpeed(50);
      configuration.setHorizontalSpeed(50);
      
      return configuration;
   }
}
