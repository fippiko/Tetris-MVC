package game.controller;

import game.model.form.Form;
import game.view.game.PreviewView;

public class PreviewController extends Controller {
   
   private Form nextForm = null;
   
   public PreviewController() {
      this.setView(new PreviewView(this));
   }
   
   @Override
   public void work() {
      super.work();
      
      
   }
   
   @Override
   public void updateView() {
      super.updateView();
      
      this.getView().updateView(this.nextForm);
   }
   
   @Override
   public PreviewView getView() {
      return (PreviewView)super.getView();
   }

   public void updateNextForm(Form nextForm) {
      this.nextForm = nextForm;
   }
}
