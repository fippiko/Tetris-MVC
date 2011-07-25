package game.controller;

import game.view.PreviewView;
import game.view.ScoreView;

public class ScoreController extends Controller {
   public ScoreController() {
      this.setView(new ScoreView(this));
   }
}
