package game.view.game;

import game.controller.Controller;
import game.model.form.Form;
import game.view.View;

import java.awt.BorderLayout;
import java.awt.Dimension;

public class PreviewView extends View {
   private static final int WIDTH    = 200;
   private static final int HEIGHT   = 350;

   private Form             nextForm = null;

   private PreviewGridView  gridView = null;

   public PreviewView(Controller controller, PreviewGridView gridView) {
      super(controller);

      this.gridView = gridView;

      this.initialize(gridView);
   }

   private void initialize(PreviewGridView gridView) {
      this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
      this.setLayout(new BorderLayout());

      this.add(gridView, BorderLayout.CENTER);
   }

   public void updateView(Form nextForm) {
      this.nextForm = nextForm;

      this.gridView.updateView(this.nextForm);
   }
}
