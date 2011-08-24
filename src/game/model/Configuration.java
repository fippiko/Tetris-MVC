package game.model;

public class Configuration {
   private String       username;
   private int          verticalSpeed;
   private int          horizontalSpeed;

   public static String CONFIGURATIONFILE = "configuration.xml";

   public String getUsername() {
      return username;
   }

   public void setUsername(String name) {
      this.username = name;
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
}
