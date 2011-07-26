package game.model.form;

import java.awt.Color;

public abstract class Form {
	public static int MAXFORMSIZE = 4;

	private int columnIndex;
	private int rowIndex;

	private int verticalSpeed;
	private int horizontalSpeed;

	public Form(int columnIndex, int rowIndex) {
		this.columnIndex = columnIndex;
		this.rowIndex = rowIndex;
	}

	public int getColumnIndex() {
		return this.columnIndex;
	}

	public int getRowIndex() {
		return this.rowIndex;
	}

	public abstract int[][] getFormMap() ;

	public abstract Color getColor();

	public void move(int column, int row) {
		this.columnIndex += column;
		this.rowIndex    += row;
	}

	public int getVerticalSpeed() {
		return this.verticalSpeed;
	}

	public int getHorizontalSpeed() {
		return this.horizontalSpeed;
	}
	
	public void setVerticalSpeed(int speed){
		this.verticalSpeed = speed;
	}
	
	public void setHorizontalSpeed(int speed){
		this.horizontalSpeed = speed;
	}

	public int getNextColumnIndex() {
		if(this.horizontalSpeed < 0){
			return this.columnIndex - 1;
		}else if(this.horizontalSpeed > 0){
			return this.columnIndex + 1;
		}
		
		return this.columnIndex;
	}

	public int getNextRowIndex() {
		if(this.verticalSpeed < 0){
			return this.rowIndex - 1;
		}else if(this.verticalSpeed > 0){
			return this.rowIndex + 1;
		}
		
		return this.rowIndex;
	}
}
