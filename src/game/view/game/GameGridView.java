package game.view.game;

import game.controller.Controller;
import game.model.FormUnit;
import game.model.form.Form;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class GameGridView extends GridView {
   private ArrayList<Form> allForms = new ArrayList<Form>();

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
         FormUnit formUnit = this.getFormUnitAtPos(gridUnit.getColumnIndex(), gridUnit.getRowIndex());
         if (formUnit != null) {
            Form form = formUnit.getForm();
            Color color = form.getColor();
            
            if(formUnit.getBlinkstate()){
               color = Form.BLINKCOLOR;
            }
            
            this.paint(g2d, gridUnit, Color.black, color, true);
         }
      }
   }

   private FormUnit getFormUnitAtPos(final int column, final int row) {
      for (Form form : this.allForms) {
         for (FormUnit formUnit : form.getUnits()) {
            if (formUnit.getColumn() == column) {
               if (formUnit.getRow() == row) {
                  return formUnit;
               }
            }
         }
      }

      return null;
   }
}
