package game.model;

import game.enums.GameAction;
import game.enums.Level;
import game.model.form.Form;

import java.util.ArrayList;
import java.util.LinkedList;

public class Game {
   private Form             activeForm = null;
   private ArrayList<Form>  allForms   = new ArrayList<Form>();
   private LinkedList<Form> nextForms  = new LinkedList<Form>();

   private GameAction       action;
   private Level            currentLevel;
   private int              score;
   private int              clearedRowsCount;

   public static final int  COLCOUNT   = 18;
   public static final int  ROWCOUNT   = 18;

   private boolean          gameover;

   public Game() {
      this.action = GameAction.NEWFORM;
      this.currentLevel = Level.Level1;
      this.score = 0;
      this.clearedRowsCount = 0;
      this.gameover = false;
   }
   public void addForm(Form newForm) {
      this.activeForm = newForm;
      this.allForms.add(newForm);
   }

   public Level getLevel() {
      return this.currentLevel;
   }

   public void setLevel(final Level level) {
      this.currentLevel = level;
   }

   public GameAction getAction() {
      return this.action;
   }

   public Form getActiveForm() {
      return this.activeForm;
   }

   public void setAction(GameAction action) {
      this.action = action;
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
      this.clearedRowsCount += rows;
   }

   public int getClearedRows() {
      return this.clearedRowsCount;
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

   public boolean getGameover() {
      return this.gameover;
   }
}
