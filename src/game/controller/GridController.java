package game.controller;

import game.view.game.GameGridView;

public class GridController extends Controller {

   public GridController() {
      this.setView(new GameGridView(this));
   }
}
