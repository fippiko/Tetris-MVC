package game.enums;

import game.helper.ResourceHelper;

public enum Resources {
   NEWGAME,
   CONTINUEGAME,
   CONFIGURATION,
   CLOSEGAME,
   CANCEL,
   BACK,
   SAVE,

   CONFIRMBACK_HEADER,
   CONFIRMBACK_BODY;

   public String getString() {
      return ResourceHelper.getString(this);
   }
}
