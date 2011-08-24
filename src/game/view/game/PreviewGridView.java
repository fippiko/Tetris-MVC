package game.view.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import game.controller.Controller;
import game.model.FormMap;
import game.model.form.Form;

public class PreviewGridView extends GridView {

   private static final int WIDTH    = 196;
   private static final int HEIGHT   = 196;

   private Form             nextForm = null;

   public PreviewGridView(Controller controller, int colCount, int rowCount) {
      super(controller, WIDTH, HEIGHT, colCount, rowCount);
   }

   @Override
   public void paintComponent(Graphics g) {
      super.paintComponent(g);

      Graphics2D g2d = (Graphics2D) g;

      if (this.nextForm != null) {
         FormMap formMap = this.nextForm.getCurrentFormMap();

         for (GridUnit gridUnit : this.getGrid().getUnits()) {
            if (formMap.containsKey(gridUnit.getColumnIndex())) {
               if (formMap.get(gridUnit.getColumnIndex()).contains(gridUnit.getRowIndex())) {
                  this.paint(g2d, gridUnit, Color.black, this.nextForm.getColor(), true);
               }
            }
         }
      }
   }

   public void updateView(Form nextForm) {
      this.nextForm = nextForm;
   }
}
