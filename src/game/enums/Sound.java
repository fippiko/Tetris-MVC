package game.enums;

public enum Sound {
   MAIN("game.mp3"),
   BLINK("blink.mp3"),
   BREAKDOWN("breakdown.mp3");

   private String              name;
   private static final String SOUNDFOLDER = "resources/sounds/";

   Sound(String name) {
      this.name = name;
   }

   public String getPath() {
      return SOUNDFOLDER + this.name;
   }
}
