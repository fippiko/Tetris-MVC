package game.controller;

import game.model.Game;
import game.view.game.GameGridView;

public class GridController extends Controller {

   public GridController() {
      this.setView(new GameGridView(this, Game.COLCOUNT, Game.ROWCOUNT));
   }
}
