package game.view.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import game.controller.Controller;
import game.model.FormUnit;
import game.model.form.Form;

public class GameGridView extends GridView {

   private static final int WIDTH    = 600;
   private static final int HEIGHT   = 600;

   private ArrayList<Form>  allForms = new ArrayList<Form>();

   public GameGridView(Controller controller, int colCount, int rowCount) {
      super(controller, WIDTH, HEIGHT, colCount, rowCount);
   }

   public void updateView(final ArrayList<Form> allForms) {
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
