package game.controller;

import game.model.form.Form;
import game.view.game.PreviewGridView;

public class PreviewGridController extends Controller {

   private Form form = null;

   public PreviewGridController() {
      this.setView(new PreviewGridView(this, 4, 4));
   }

   public void updateForm(Form form) {
      this.form = form;
   }

   @Override
   public PreviewGridView getView() {
      return (PreviewGridView) super.getView();
   }

   @Override
   public void updateView() {
      super.updateView();
   }
}
