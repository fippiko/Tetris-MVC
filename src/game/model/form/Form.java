package game.model.form;

import java.awt.Color;

public abstract class Form {
   public static int MAXFORMSIZE = 4;

   private int       columnIndex;
   private int       rowIndex;

   private int       verticalSpeed;
   private int       horizontalSpeed;

   public Form(int columnIndex, int rowIndex) {
      this.columnIndex = columnIndex;
      this.rowIndex = rowIndex;
      
      this.verticalSpeed = 0;
      this.horizontalSpeed = 0;
   }

   public int getColumnIndex() {
      return this.columnIndex;
   }

   public int getRowIndex() {
      return this.rowIndex;
   }

   public abstract int[][] getFormMap();

   public abstract Color getColor();

   public void setPosition(int newColumn, int newRow){
      this.columnIndex = newColumn;
      this.rowIndex = newRow;
   }
   
   public int getVerticalSpeed() {
      return this.verticalSpeed;
   }

   public int getHorizontalSpeed() {
      return this.horizontalSpeed;
   }

   public void setVerticalSpeed(int speed) {
      this.verticalSpeed = speed;
   }

   public void setHorizontalSpeed(int speed) {
      this.horizontalSpeed = speed;
   }

   public abstract int getCurrentHeight();
}
