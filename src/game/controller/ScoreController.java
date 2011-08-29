package game.controller;

import game.view.game.ScoreView;

public class ScoreController extends Controller {
   public ScoreController(Controller parentController) {
      super(parentController);
   }
   
   @Override
   protected boolean initialize() {
      this.setView(new ScoreView(this));
      
      return true;
   }
}
