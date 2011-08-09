package game.model;

public class FormUnit {
   
   private int column;
   private int row;
   
   public FormUnit(final int column, final int row){
      this.column = column;
      this.row = row;
   }
   
   public int getColumn(){
      return this.column;
   }
   
   public int getRow(){
      return this.row;
   }
   
   public void setColumn(int newColumn){
      this.column = newColumn;
   }
   
   public void setRow(int newRow){
      this.row = newRow;
   }
}
