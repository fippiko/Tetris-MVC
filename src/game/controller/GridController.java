package game.controller;

import game.view.GameGridView;

public class GridController extends Controller {

   public GridController() {
      this.setView(new GameGridView(this));
   }
}
