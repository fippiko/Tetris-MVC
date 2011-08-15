package game.model;

public class Configuration {
   private String name;
   private int    verticalSpeed;
   private int    horizontalSpeed;

   private String configurationFile;
   
   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public int getVerticalSpeed() {
      return verticalSpeed;
   }

   public void setVerticalSpeed(int verticalSpeed) {
      this.verticalSpeed = verticalSpeed;
   }

   public int getHorizontalSpeed() {
      return horizontalSpeed;
   }

   public void setHorizontalSpeed(int horizontalSpeed) {
      this.horizontalSpeed = horizontalSpeed;
   }

   public String getConfigurationFileName() {
      return configurationFile;
   }

   public void setConfigurationFile(String configurationFile) {
      this.configurationFile = configurationFile;
   }
}
