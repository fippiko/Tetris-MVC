package game.controller;

import game.enums.GameState;
import game.helper.CollisionHelper;
import game.helper.ConfigurationHelper;
import game.helper.FormHelper;
import game.helper.TimeHelper;
import game.model.Game;
import game.model.form.Form;
import game.view.game.GameGridView;
import game.view.game.GameView;
import game.view.game.PreviewView;
import game.view.game.ScoreView;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class GameController extends Controller {
   private PreviewController  previewController;
   private ScoreController    scoreController;
   private GameGridController gridController;

   private Game               game;

   private LinkedList<Form>   nextForms;

   public GameController() {
      this.previewController = new PreviewController();
      this.scoreController = new ScoreController();
      this.gridController = new GameGridController();

      this.addSubcontroller(this.previewController);
      this.addSubcontroller(this.scoreController);
      this.addSubcontroller(this.gridController);

      PreviewView previewView = this.previewController.getView();
      ScoreView scoreView = (ScoreView) this.scoreController.getView();
      GameGridView gameGridView = this.gridController.getView();

      this.setView(new GameView(this, previewView, scoreView, gameGridView));

      this.game = new Game();
      this.nextForms = new LinkedList<Form>();
   }

   @Override
   public void work() {
      super.work();

      switch (this.game.getState()) {
         case NEXTFORM :
            this.addNewForm(this.game);
            break;
         case MOVEFORM :
            this.doMovement(this.game);
            break;
         case BREAKDOWN :
            this.doBreakdown(this.game);
            this.game.setState(GameState.NEXTFORM);
            break;

         case GAMEOVER :
            this.doGameover(this.game);
            break;
      }

      this.gridController.updateForms(this.game.getAllForms());
      this.previewController.updateNextForm(this.nextForms.getFirst());
   }

   private boolean hasActiveFormStopped(Game game) {
      Form activeForm = game.getActiveForm();

      return !CollisionHelper.checkVerticalCollision(activeForm, 1, game.getDeadForms());
   }

   private void doGameover(Game game) {
      // TODO Auto-generated method stub

   }

   private long getHorizontalSpeedInterval() {
      int horizontalSpeed = ConfigurationHelper.getConfiguration().getHorizontalSpeed();

      return 200 - horizontalSpeed;
   }

   private long getVerticalSpeedInterval(final int level) {
      int verticalInterval = 500 - level * 9;

      if (this.isKeyPressed(KeyEvent.VK_DOWN)) {
         verticalInterval = 100;
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
         boolean verticalCollision = !FormHelper.moveFormVertical(activeForm, verticalDelta, game.getDeadForms());

         if (verticalCollision) {
            this.game.setState(GameState.BREAKDOWN);
         }
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

      // add two forms if there're no of them yet
      if (this.nextForms.size() == 0) {
         this.nextForms.add(FormHelper.generateRandomForm(startcol, 0));
      }

      // add the first form in the list to the game and remove it from the list
      game.addForm(this.nextForms.poll());

      // now add a new element to the end
      this.nextForms.add(FormHelper.generateRandomForm(startcol, 0));
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
   public void keyPressed(KeyEvent keyEvent) {
      super.keyPressed(keyEvent);

      int keyCode = keyEvent.getKeyCode();
      if (keyCode == KeyEvent.VK_ESCAPE) {
         this.close();
      }
      if (keyCode == KeyEvent.VK_SPACE) {
         this.rotateForm();
      }
   }
}
