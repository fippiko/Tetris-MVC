package game.controller;

import game.model.Game;
import game.model.form.Form;
import game.view.game.GameGridView;

import java.util.ArrayList;

public class GameGridController extends ControllerBase {

   private ArrayList<Form> allForms = null;

   public GameGridController(ControllerBase parentController) {
      super(parentController);
   }
   
   @Override
   protected boolean initialize() {      
      this.setView(new GameGridView(this, Game.COLCOUNT, Game.ROWCOUNT));
      
      return true;
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
