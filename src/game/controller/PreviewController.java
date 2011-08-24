package game.controller;

import game.model.form.Form;
import game.view.game.PreviewGridView;
import game.view.game.PreviewView;

public class PreviewController extends Controller {

   private PreviewGridController gridController;

   private Form                  nextForm = null;

   public PreviewController() {
      this.gridController = new PreviewGridController();

      this.addSubcontroller(this.gridController);

      PreviewGridView gridView = this.gridController.getView();

      this.setView(new PreviewView(this, gridView));
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
      return (PreviewView) super.getView();
   }

   public void updateNextForm(Form nextForm) {
      this.nextForm = nextForm;
   }
}
