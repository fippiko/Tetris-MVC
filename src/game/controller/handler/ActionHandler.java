package game.controller.handler;

import game.controller.Controller;

public abstract class ActionHandler {
   private Controller controller = null;

   public ActionHandler(Controller controller) {
      this.controller = controller;
   }

   protected Controller getController() {
      return this.controller;
   }
}
