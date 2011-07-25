package game.controller;

import game.view.PreviewView;

public class PreviewController extends Controller {
   public PreviewController() {
      this.setView(new PreviewView(this));
   }
}
