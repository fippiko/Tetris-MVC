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
   private static final int WIDTH       = 600;
   private static final int HEIGHT      = 600;

   private GameGrid         grid;

   private ArrayList<Form>  activeForms = new ArrayList<Form>();

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

   private void paint(Graphics2D g2d, GameGridUnit gridUnit, Color color, Boolean fill) {
      g2d.setColor(color);
      g2d.drawRect(gridUnit.getX(), gridUnit.getY(), gridUnit.getWidth(), gridUnit.getHeight());

      Form form = this.getFormOnGridUnit(gridUnit);
      if (form != null) {
         g2d.setColor(form.getColor());
         g2d.fillRect(gridUnit.getX() + 1, gridUnit.getY() + 1, gridUnit.getWidth() - 1, gridUnit.getHeight() - 1);
      }
   }

   public ArrayList<GameGridUnit> getGridUnitsOfForm(Form form) {
      ArrayList<GameGridUnit> gridUnits = new ArrayList<GameGridUnit>();

      int[][] formMap = form.getFormMap();

      int maxColumnIndex = form.getColumnIndex() + Form.MAXFORMSIZE;
      int maxRowIndex = form.getRowIndex() + Form.MAXFORMSIZE;

      int xincrementer = 0;
      for (int columnIndex = form.getColumnIndex(); columnIndex < maxColumnIndex; columnIndex++) {
         int yincrementer = 0;
         for (int rowIndex = form.getRowIndex(); rowIndex < maxRowIndex; rowIndex++) {
            if (formMap[xincrementer][yincrementer] == 1) {
               gridUnits.add(this.grid.getUnit(columnIndex, rowIndex));
            }
            yincrementer++;
         }
         xincrementer++;
      }

      return gridUnits;
   }

   private Form getFormOnGridUnit(GameGridUnit gridUnit) {
      for (Form form : this.activeForms) {
         if (this.getGridUnitsOfForm(form).contains(gridUnit)) {
            return form;
         }
      }

      return null;
   }

   public void updateView(ArrayList<Form> activeForms) {
      this.activeForms = activeForms;
   }
}
