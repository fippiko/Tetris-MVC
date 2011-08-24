package game.view.game;

import game.controller.Controller;
import game.model.FormUnit;
import game.model.form.Form;
import game.view.View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class GameGridView extends View {
   private static final int WIDTH      = 600;
   private static final int HEIGHT     = 600;

   private GameGrid         grid;

   //private Form             activeForm = null;
   private ArrayList<Form>  allForms   = new ArrayList<Form>();

   public GameGridView(Controller controller, int colCount, int rowCount) {
      super(controller);

      this.setPreferredSize(new Dimension(WIDTH, HEIGHT));

      this.grid = new GameGrid(0, 0, WIDTH, HEIGHT, colCount, rowCount);
   }

   @Override
   public void paintComponent(Graphics g) {
      super.paintComponent(g);

      Graphics2D g2d = (Graphics2D) g;

      for (GameGridUnit gridUnit : this.grid.getUnits()) {
         this.paint(g2d, gridUnit, Color.black, false);
      }
   }

   private void paint(Graphics2D g2d, GameGridUnit gridUnit, Color color, boolean fill) {
      g2d.setColor(color);
      g2d.drawRect(gridUnit.getX(), gridUnit.getY(), gridUnit.getWidth(), gridUnit.getHeight());

      Form form = this.getFormAtPos(gridUnit.getColumnIndex(), gridUnit.getRowIndex());
      if (form != null) {
         g2d.setColor(form.getColor());
         g2d.fillRect(gridUnit.getX() + 1, gridUnit.getY() + 1, gridUnit.getWidth() - 1, gridUnit.getHeight() - 1);
      }
   }
   
   private Form getFormAtPos(final int column, final int row){
      for (Form form : this.allForms) {
         for (FormUnit formUnit : form.getUnits()) {
            if(formUnit.getColumn() == column){
               if(formUnit.getRow() == row){
                  return form;
               }
            }
         }
      }
      
      return null;
   }
   
   public void updateView(/*final Form activeForm, */final ArrayList<Form>  allForms) {
      this.allForms = allForms;
   }
}
