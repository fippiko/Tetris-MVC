package game.view.game;

import game.controller.Controller;
import game.model.form.Form;
import game.view.View;

import java.awt.BorderLayout;
import java.awt.Dimension;

public class PreviewView extends View {
   private PreviewGridView  gridView = null;

   public PreviewView(Controller controller, final int width, final int height) {
      super(controller, width, height);

      this.gridView = new PreviewGridView(controller, 196, 196, 4, 4);

      this.initialize(gridView);
   }

   private void initialize(PreviewGridView gridView) {
      this.setLayout(new BorderLayout());

      this.add(gridView, BorderLayout.CENTER);
   }
   
   public void updateNextForm(Form nextForm) {
      this.gridView.updateView(nextForm);
   }
}
