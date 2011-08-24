package game.view.game;

import game.controller.Controller;
import game.model.FormUnit;
import game.model.form.Form;
import game.view.View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class PreviewView extends View {

   private static final int WIDTH  = 200;
   private static final int HEIGHT = 350;
   
   private Form nextForm = null;

   public PreviewView(Controller controller) {
      super(controller);

      this.initialize();
   }

   private void initialize() {
      this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
   }

   @Override
   public void paintComponent(Graphics g) {
      super.paintComponent(g);

      Graphics2D g2d = (Graphics2D) g;

      if(this.nextForm != null){
         /*for(int columnIndex = -1; columnIndex < 3; columnIndex++){
            for(int rowIndex = -1; rowIndex < 3; rowIndex ++){
               for (FormUnit unit : this.nextForm.getUnits()) {
                  if(unit.getColumn() == columnIndex){
                     if(unit.getRow() == rowIndex){
                        
                     }
                  }
               }
            }
         }*/
      }
   }

   public void updateView(Form nextForm) {
      this.nextForm = nextForm;
   }
}
