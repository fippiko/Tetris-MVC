package game.controller;

import game.view.game.PreviewView;

public class PreviewController extends Controller {
   public PreviewController() {
      this.setView(new PreviewView(this));
   }
}
