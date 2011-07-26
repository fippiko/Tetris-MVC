package game.controller;

import game.view.game.ScoreView;

public class ScoreController extends Controller {
   public ScoreController() {
      this.setView(new ScoreView(this));
   }
}
