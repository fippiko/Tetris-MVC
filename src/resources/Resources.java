package resources;

public enum Resources {
   NEWGAME,
   CONFIGURATION,
   CLOSEGAME,
   CANCEL,
   BACK,
   SAVE,
   
   CONFIRMBACK_HEADER,
   CONFIRMBACK_BODY;
   
   public String getString(){
      return ResourceManager.getString(this);
   }
}
