package game.model;

import game.model.form.Form;

public class FormUnit {

   private int column;
   private int row;
   
   private Form form;
   
   private boolean blinkstate;

   public FormUnit(Form form, final int column, final int row) {
      this.column = column;
      this.row = row;
      this.form = form;
      this.blinkstate = false;
   }

   public int getColumn() {
      return this.column;
   }

   public int getRow() {
      return this.row;
   }

   public void setColumn(int newColumn) {
      this.column = newColumn;
   }

   public void setRow(int newRow) {
      this.row = newRow;
   }

   public void setBlinkState(boolean light) {
      this.blinkstate = light;
   }
   
   public boolean getBlinkstate(){
      return this.blinkstate;
   }

   public Form getForm() {
      return this.form;
   }
}
