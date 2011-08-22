package game.controller;

import java.util.ArrayList;

import game.model.Game;
import game.model.form.Form;
import game.view.View;
import game.view.game.GameGridView;

public class GridController extends Controller {
   
   private ArrayList<Form> allForms = null;

   public GridController() {
      this.setView(new GameGridView(this, Game.COLCOUNT, Game.ROWCOUNT));
   }

   public void updateForms(ArrayList<Form> allForms) {
      this.allForms = allForms;
   }
   
   @Override
   public GameGridView getView() {
      return (GameGridView) super.getView();
   }
   
   @Override
   public void updateView() {
      this.getView().updateView(this.allForms);
      
      super.updateView();
   }
}
