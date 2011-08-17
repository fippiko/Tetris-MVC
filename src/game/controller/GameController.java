package game.controller;

import game.enums.GameState;
import game.helper.CollisionHelper;
import game.helper.ConfigurationHelper;
import game.helper.FormHelper;
import game.helper.TimeHelper;
import game.model.Configuration;
import game.model.Game;
import game.model.form.Form;
import game.view.game.GameGridView;
import game.view.game.GameView;
import game.view.game.PreviewView;
import game.view.game.ScoreView;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class GameController extends Controller {
   private PreviewController previewController;
   private ScoreController   scoreController;
   private GridController    gridController;

   private Game              game;

   public GameController() {
      this.previewController = new PreviewController();
      this.scoreController = new ScoreController();
      this.gridController = new GridController();

      this.addSubcontroller(this.previewController);
      this.addSubcontroller(this.scoreController);
      this.addSubcontroller(this.gridController);

      PreviewView previewView = (PreviewView) this.previewController.getView();
      ScoreView scoreView = (ScoreView) this.scoreController.getView();
      GameGridView gameGridView = (GameGridView) this.gridController.getView();

      this.setView(new GameView(this, previewView, scoreView, gameGridView));

      this.game = new Game();
   }

   @Override
   public void work() {
      super.work();

      switch (this.game.getState()) {
         case NEXTFORM :
            this.addNewForm(this.game);
            break;
         case FORMACTIVE :
            this.doMovement(this.game);
            this.doBreakdown(this.game);
            break;
      }

      this.getView().updateView(this.game);
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
         if (CollisionHelper.checkHorizontalCollision(activeForm, horizontalDelta, game.getDeadForms())) {
            activeForm.moveHorizontal(horizontalDelta);
         }
      }

      // do vertical movement on vertical-interval
      if (TimeHelper.timeReached(this, "moveVertical", this.getVerticalSpeedInterval(game.getLevel()))) {
         int verticalDelta = this.getVerticalDelta();
         if (CollisionHelper.checkVerticalCollision(activeForm, verticalDelta, game.getDeadForms())) {
            activeForm.moveVertical(verticalDelta);
         }
         else {
            this.doBreakdown(game);

            // TODO don't set the state here...
            game.setState(GameState.NEXTFORM);
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

      game.addForm(FormHelper.generateRandomForm(startcol, 0));
   }

   private void rotateForm() {
      Form activeForm = this.game.getActiveForm();
      ArrayList<Form> otherForms = this.game.getDeadForms();

      FormHelper.rotateActiveForm(activeForm, otherForms);
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
