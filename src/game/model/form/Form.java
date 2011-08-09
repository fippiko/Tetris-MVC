package game.model.form;

import game.model.FormUnit;

import java.awt.Color;
import java.util.ArrayList;

public abstract class Form {
   public static int MAXFORMSIZE = 4;

   //private int       columnIndex;
   //private int       rowIndex;

   private int       verticalSpeed;
   private int       horizontalSpeed;
   
   private ArrayList<FormUnit> units;

   public Form(int columnIndex, int rowIndex) {
      this.verticalSpeed = 0;
      this.horizontalSpeed = 0;
      
      this.units = this.generateUnits(columnIndex, rowIndex);
   }
/*
   public int getColumnIndex() {
      return this.columnIndex;
   }
   
   public void setColumnIndex(int newColumnIndex) {
      this.columnIndex = newColumnIndex;
   }

   public int getRowIndex() {
      return this.rowIndex;
   }
   
   public void setRowIndex(int newRowIndex){
      this.rowIndex = newRowIndex;
   }
*/
   public abstract ArrayList<FormUnit> generateUnits(int columnIndex, int rowIndex);
   
   public ArrayList<FormUnit> getUnits(){
      return this.units;
   }

   public abstract Color getColor();

   
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
   public abstract int getCurrentWidth();
   
   public void moveHorizontal(int horizontalDelta) {
      for (FormUnit unit : this.units) {
         unit.setColumn(unit.getColumn() + horizontalDelta);
      }
   }

   public void moveVertical(int verticalDelta) {
      for (FormUnit unit : this.units) {
         unit.setRow(unit.getRow() + verticalDelta);
      }
   }
}
