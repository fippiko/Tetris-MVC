package game.model;

import game.enums.GameState;
import game.model.form.Form;

import java.util.ArrayList;

public class Game {
   private Form            activeForm = null;
   private ArrayList<Form> allForms   = new ArrayList<Form>();

   private GameState       state;
   private int             currentLevel;
   private int             score;
   private int             clearedRows;

   public static final int COLCOUNT   = 18;
   public static final int ROWCOUNT   = 18;

   public Game() {
      this.state = GameState.NEXTFORM;
      this.currentLevel = 1;
      this.score = 0;
      this.clearedRows = 0;
   }

   public void addForm(Form newForm) {
      this.activeForm = newForm;
      this.allForms.add(newForm);

      this.state = GameState.MOVEFORM;
   }

   public int getLevel() {
      return this.currentLevel;
   }

   public void setLevel(final int level) {
      this.currentLevel = level;
   }

   public GameState getState() {
      return this.state;
   }

   public Form getActiveForm() {
      return this.activeForm;
   }

   public void setState(GameState state) {
      this.state = state;
   }

   public ArrayList<Form> getAllForms() {
      return this.allForms;
   }

   public ArrayList<Form> getDeadForms() {
      Form activeForm = this.getActiveForm();
      ArrayList<Form> otherForms = new ArrayList<Form>(this.getAllForms());
      otherForms.remove(activeForm);

      return otherForms;
   }

   public int getScore() {
     return this.score;
   }

   public void addClearedRows(int rows) {
      this.clearedRows += rows;
   }

   public int getClearedRows() {
      return this.clearedRows;
   }

   public void setScore(int currentScore) {
      this.score = currentScore;
   }
}
