package game.view.game;

import game.controller.Controller;
import game.model.FormUnit;
import game.model.form.Form;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class GameGridView extends GridView {
   private ArrayList<Form>  allForms = new ArrayList<Form>();

   public GameGridView(Controller controller, final int width, final int height, int colCount, int rowCount) {
      super(controller, width, height, colCount, rowCount);
   }

   public void updateAllForms(final ArrayList<Form> allForms) {
      this.allForms = allForms;
   }

   @Override
   public void paintComponent(Graphics g) {
      super.paintComponent(g);

      Graphics2D g2d = (Graphics2D) g;

      for (GridUnit gridUnit : this.getGrid().getUnits()) {
         Form form = this.getFormAtPos(gridUnit.getColumnIndex(), gridUnit.getRowIndex());
         if (form != null) {
            this.paint(g2d, gridUnit, Color.black, form.getColor(), true);
         }
      }
   }

   private Form getFormAtPos(final int column, final int row) {
      for (Form form : this.allForms) {
         for (FormUnit formUnit : form.getUnits()) {
            if (formUnit.getColumn() == column) {
               if (formUnit.getRow() == row) {
                  return form;
               }
            }
         }
      }

      return null;
   }
}
