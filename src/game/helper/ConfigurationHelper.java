package game.helper;

import java.io.File;

import game.model.Configuration;

public abstract class ConfigurationHelper {
   private static Configuration configurationInstance = null;
   
   public static Configuration getInstance(){
      if(configurationInstance == null){
         configurationInstance = new Configuration();
         initializeConfiguration(configurationInstance);
      }
      
      return configurationInstance;
   }
   
   public static void saveConfiguration(Configuration configuration){
      SerializationHelper.writeObjectToXml(configuration, configuration.getConfigurationFileName());
   }
   
   private static void initializeConfiguration(Configuration configuration){
      String configurationFilePath = "";
      if(configuration.getConfigurationFileName() != null){
         configurationFilePath = configuration.getConfigurationFileName();
      }
      
      if(new File(configurationFilePath).exists()){
         configuration = (Configuration)SerializationHelper.readObjectFromXml(configurationFilePath);
      }else{
         setDefaultValues(configurationInstance);
      }
   }
   
   private static void setDefaultValues(Configuration configuration){
      configuration.setName("Unkown");
      configuration.setConfigurationFile("configuration.xml");
      configuration.setVerticalSpeed(50);
      configuration.setHorizontalSpeed(50);
      
   }
}
