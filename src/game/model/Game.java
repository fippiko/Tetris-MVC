package game.model;

import game.enums.GameAction;
import game.model.form.Form;

import java.util.ArrayList;
import java.util.LinkedList;

public class Game {
   private Form             activeForm = null;
   private ArrayList<Form>  allForms   = new ArrayList<Form>();
   private LinkedList<Form> nextForms  = new LinkedList<Form>();

   private GameAction        state;
   private int              currentLevel;
   private int              score;
   private int              clearedRows;

   public static final int  COLCOUNT   = 18;
   public static final int  ROWCOUNT   = 18;
   
   private boolean gameover;

   public Game() {
      this.state = GameAction.NEXTFORM;
      this.currentLevel = 1;
      this.score = 0;
      this.clearedRows = 0;
      this.gameover = false;
   }
   public void addForm(Form newForm) {
      this.activeForm = newForm;
      this.allForms.add(newForm);
   }

   public int getLevel() {
      return this.currentLevel;
   }

   public void setLevel(final int level) {
      this.currentLevel = level;
   }

   public GameAction getState() {
      return this.state;
   }

   public Form getActiveForm() {
      return this.activeForm;
   }

   public void setState(GameAction state) {
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

   public final LinkedList<Form> getNextForms() {
      return this.nextForms;
   }
   public Form pollNextForm() {
      return this.nextForms.poll();
   }
   public void addNextForm(Form nextForm) {
      this.nextForms.add(nextForm);
   }
   public Form getNextForm() {
      return this.nextForms.getFirst();
   }
   public void setGameover(boolean gameover) {
      this.gameover = gameover;
   }
   
   public boolean getGameover(){
      return this.gameover;
   }
}
