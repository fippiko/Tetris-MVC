package game.view.game;

import game.controller.Controller;
import game.model.form.Form;
import game.view.View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class GameGridView extends View {
   private int      width  = 600;
   private int      height = 600;

   private GameGrid grid;
   
   private ArrayList<Form> activeForms = null;

   public GameGridView(Controller controller) {
      super(controller);

      this.setPreferredSize(new Dimension(this.width, this.height));

      this.grid = new GameGrid(0, 0, this.width, this.height, 18, 18);
   }

   @Override
   public void paintComponent(Graphics g) {
      super.paintComponent(g);

      Graphics2D g2d = (Graphics2D) g;

      for (GameGridUnit gridUnit : this.grid.getUnits()) {
         this.paint(g2d, gridUnit, Color.black, false);
      }
   }

   private void paint(Graphics2D g2d, GameGridUnit gridUnit, Color color, Boolean fill) {
      // draw the border
      g2d.drawRect(gridUnit.getX(), gridUnit.getY(), gridUnit.getWidth(), gridUnit.getHeight());

      if (this.isFormOnGridUnit(gridUnit)) {
         g2d.setColor(color);
         g2d.fillRect(gridUnit.getX() + 1, gridUnit.getY() + 1, gridUnit.getWidth() - 1, gridUnit.getHeight() - 1);
      }
   }

   public ArrayList<GameGridUnit> getGridUnitsOfForm(Form form)
   {
      ArrayList<GameGridUnit> gridUnits = new ArrayList<GameGridUnit>();
      
      int[][] formMap = form.getFormMap();
      
      int maxColumnIndex = form.getColumnIndex() + Form.MAXFORMSIZE;
      int maxRowIndex = form.getRowIndex() + Form.MAXFORMSIZE;
      
      int xincrementer = 0;
      for(int columnIndex = form.getColumnIndex(); columnIndex < maxColumnIndex; columnIndex++){
         int yincrementer = 0;
         for(int rowIndex = form.getRowIndex(); rowIndex < maxRowIndex; rowIndex++){
            if(formMap[xincrementer][yincrementer] == 1){
               gridUnits.add(this.grid.getUnit(columnIndex, rowIndex));
            }
            yincrementer++;
         }
         xincrementer++;
      }
      
      return gridUnits;
   }
   
   private boolean isFormOnGridUnit(GameGridUnit gridUnit) {
      for (Form form : this.activeForms) {
         if(this.getGridUnitsOfForm(form).contains(gridUnit)){
            return true;
         }
      }
      
      return false;
   }

   public void updateView(ArrayList<Form> activeForms) {
      this.activeForms = activeForms;
   }
}
