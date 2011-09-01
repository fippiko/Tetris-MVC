package game.controller;

import game.enums.GameState;
import game.helper.CollisionHelper;
import game.helper.ConfigurationHelper;
import game.helper.FormHelper;
import game.helper.LevelHelper;
import game.helper.ScoreHelper;
import game.helper.TimeHelper;
import game.model.FormUnit;
import game.model.Game;
import game.model.form.Form;
import game.view.game.GameGridView;
import game.view.game.GameView;
import game.view.game.GameoverView;
import game.view.game.InformationView;
import game.view.game.PreviewView;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class GameController extends Controller {
   private Game game;

   public GameController(Controller parentController) {
      super(parentController);
   }

   @Override
   protected boolean initialize() {
      this.setView(new GameView(this));

      this.game = new Game();

      return true;
   }

   @Override
   public void work() {
      super.work();

      switch (this.game.getState()) {
         case NEXTFORM :
            this.addNewForm(this.game);
            this.game.setState(GameState.MOVEFORM);

            if (this.checkForGameover(this.game)) {
               this.game.setState(GameState.GAMEOVER);
            }
            break;
         case MOVEFORM :
            this.doMovement(this.game);

            if (this.checkForBreakdown(this.game)) {
               this.game.setState(GameState.BREAKDOWN);
            }
            break;
         case INSTANTDOWN :
            this.doInstantDown(game);
            this.game.setState(GameState.BREAKDOWN);
            break;
         case BREAKDOWN :
            this.doBreakdown(this.game);
            this.game.setState(GameState.NEXTFORM);
            break;
         case GAMEOVER :
            this.doGameover(this.game);
            break;
         case NEWGAME :
            this.clearSubController();
            this.initialize();
            break;
      }
   }

   @Override
   public MainController getParentController() {
      return (MainController) super.getParentController();
   }

   private boolean checkForBreakdown(Game game) {
      boolean verticalCollided = false;
      boolean lastChanceUsed = false;

      Form activeForm = game.getActiveForm();

      verticalCollided = !CollisionHelper.checkVerticalCollision(activeForm, 1, game.getDeadForms());

      if (verticalCollided) {
         lastChanceUsed = TimeHelper.timeReached(this, "checkForBreakdown", this.getVerticalSpeedInterval(game.getLevel()));
      }
      else {
         TimeHelper.resetTime(this, "checkForBreakdown");
      }

      return verticalCollided && lastChanceUsed;
   }

   private void doGameover(Game game) {
      game.setGameover(true);
   }

   private long getHorizontalSpeedInterval() {
      int horizontalSpeed = ConfigurationHelper.getConfiguration().getHorizontalSpeed();

      return horizontalSpeed;
   }

   private long getVerticalSpeedInterval(final int level) {
      int verticalInterval = 500 - level * 18;

      if (this.isKeyPressed(KeyEvent.VK_DOWN)) {
         verticalInterval = 50;
      }

      return verticalInterval;
   }

   private void doMovement(Game game) {
      Form activeForm = game.getActiveForm();

      // do horizontal movement on horizontal-interval
      if (TimeHelper.timeReached(this, "moveHorizontal", this.getHorizontalSpeedInterval())) {
         int horizontalDelta = this.getHorizontalDelta();
         FormHelper.moveFormHorizontal(activeForm, horizontalDelta, game.getDeadForms());
      }

      // do vertical movement on vertical-interval
      if (TimeHelper.timeReached(this, "moveVertical", this.getVerticalSpeedInterval(game.getLevel()))) {
         int verticalDelta = this.getVerticalDelta();
         FormHelper.moveFormVertical(activeForm, verticalDelta, game.getDeadForms());
      }
   }

   private void doBreakdown(Game game) {

      ArrayList<Integer> filledRows = FormHelper.getFilledRows(game.getAllForms());
      Collections.sort(filledRows);

      if (filledRows.size() > 0) {

         for (Integer filledRowIndex : filledRows) {
            FormHelper.removeAllUnitsOnRow(game.getAllForms(), filledRowIndex);
         }

         int highestRow = filledRows.get(filledRows.size() - 1);
         for (int rowIndex = highestRow; rowIndex >= 0; rowIndex--) {
            FormHelper.moveAllUnitsOnRowDown(game.getAllForms(), rowIndex, filledRows.size());
         }
      }

      this.game.addClearedRows(filledRows.size());
      this.game.setLevel(LevelHelper.getCurrentLevel(this.game.getClearedRows()));
      this.game.setScore(ScoreHelper.getCurrentScore(this.game.getClearedRows()));
   }

   private int getVerticalDelta() {
      return 1;
   }

   private int getHorizontalDelta() {
      int delta = 0;

      if (this.isKeyPressed(KeyEvent.VK_LEFT)) {
         delta = -1;
      }
      if (this.isKeyPressed(KeyEvent.VK_RIGHT)) {
         delta = 1;
      }

      return delta;
   }

   private void addNewForm(Game game) {
      int startcol = 8;

      // get the first form from the list and remove it from it
      Form nextForm = this.game.pollNextForm();

      if (nextForm == null) {
         nextForm = FormHelper.generateRandomForm(startcol, -1);
      }

      // add the first form in the list to the game
      game.addForm(nextForm);

      // now add a new element to the end
      game.addNextForm(FormHelper.generateRandomForm(startcol, -1));
   }

   private boolean checkForGameover(Game game) {
      Form activeForm = game.getActiveForm();

      boolean rowZero = false;
      boolean collision = false;
      boolean gameover = false;

      for (FormUnit unit : activeForm.getUnits()) {
         if (unit.getRow() <= 0) {
            rowZero = true;
         }
      }

      collision = !CollisionHelper.checkVerticalCollision(activeForm, 0, game.getDeadForms());

      gameover = collision && rowZero;

      return gameover;
   }

   private void rotateForm() {
      Form activeForm = this.game.getActiveForm();
      ArrayList<Form> otherForms = this.game.getDeadForms();

      FormHelper.rotateForm(activeForm, otherForms);
   }

   @Override
   public GameView getView() {
      return (GameView) super.getView();
   }

   @Override
   public void handleKey(KeyEvent keyEvent) {

      int keyCode = keyEvent.getKeyCode();
      switch (keyCode) {
         case KeyEvent.VK_ESCAPE :
            this.close();
            break;
         case KeyEvent.VK_SPACE :
            if (!this.game.getGameover()) {
               this.rotateForm();
            }
            else {
               this.startNewGame();
            }
            break;
         case KeyEvent.VK_F :
            int currentLevel = this.game.getLevel();
            this.game.setLevel(currentLevel + 1);
            break;
         case KeyEvent.VK_ENTER :
            this.game.setState(GameState.INSTANTDOWN);
            break;

      }
   }

   private void doInstantDown(Game game) {
      Form activeForm = game.getActiveForm();

      boolean formIsDown = false;
      while (!formIsDown) {
         formIsDown = !FormHelper.moveFormVertical(activeForm, 1, game.getDeadForms());
      }
   }

   public void startNewGame() {
      this.game.setState(GameState.NEWGAME);
   }

   @Override
   public void updateView() {
      this.getView().updateAttributes(this.game);
      super.updateView();
   }
}
