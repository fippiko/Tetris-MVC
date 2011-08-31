package game.controller;

import game.model.form.Form;
import game.view.game.PreviewView;

public class PreviewController extends ControllerBase {

   private Form nextForm = null;

   public PreviewController(ControllerBase parentController) {
      super(parentController);
   }

   @Override
   protected boolean initialize() {
      this.setView(new PreviewView(this));

      return true;
   }

   @Override
   public void work() {
      super.work();
   }

   @Override
   public void updateView() {
      super.updateView();

      if (this.nextForm != null) {
         this.getView().updateView(this.nextForm);
      }
   }

   @Override
   public PreviewView getView() {
      return (PreviewView) super.getView();
   }

   public void updateNextForm(Form nextForm) {
      this.nextForm = nextForm;
   }
}
