package game.controller;

import game.view.game.InformationView;

public class InformationController extends Controller {
   private int score = 0;
   private int level = 0;
   
   public InformationController(Controller parentController) {
      super(parentController);
   }
   
   @Override
   protected boolean initialize() {
      this.setView(new InformationView(this));
      
      return true;
   }

   public void updateScore(int score) {
      this.score = score;
   }

   public void updateLevel(int level) {
      this.level = level;
   }
   
   @Override
   public void updateView() {
      this.getView().updateScore(this.score);
      this.getView().updateLevel(this.level);
      
      super.updateView();
   }
   
   @Override
   public InformationView getView() {
      return (InformationView)super.getView();
   }
}
