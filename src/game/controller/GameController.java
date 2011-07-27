package game.controller;

import game.enums.GameState;
import game.helper.CollisionHelper;
import game.helper.FormHelper;
import game.helper.TimeHelper;
import game.model.Game;
import game.model.form.Form;
import game.model.form.FormBlock;
import game.model.form.FormL;
import game.view.game.GameGridView;
import game.view.game.GameView;
import game.view.game.PreviewView;
import game.view.game.ScoreView;

import java.awt.event.KeyEvent;

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
            this.game.addForm(this.generateRandomForm());
            break;
         case FORMACTIVE :
            if (TimeHelper.timeReached(this, 200, true)) {
               this.moveForm(this.game.getActiveForm());
            }
            break;
      }

      this.getView().updateView(game);
   }

   private Form generateRandomForm() {
      int startcol = 5;

      Form randomForm = new FormL(startcol, 0);
      
      randomForm.setVerticalSpeed(1);
      
      return randomForm;
   }

   private void moveForm(Form form) {
      int nextColumn = FormHelper.calculateNextColumnIndex(form);
      int nextRow = FormHelper.calculateNextRowIndex(form);

      if (CollisionHelper.checkHorizontalCollision(form, this.game.getActiveForms())) {
         form.setHorizontalSpeed(0);
      }
      if (CollisionHelper.checkVerticalCollision(form, this.game.getActiveForms())) {
         form.setVerticalSpeed(0);
         this.game.setState(GameState.NEXTFORM);
      }

      this.game.getActiveForm().setPosition(nextColumn, nextRow);
   }

   @Override
   public GameView getView() {
      return (GameView) super.getView();
   }

   @Override
   public void executeKey(KeyEvent keyEvent) {
      super.executeKey(keyEvent);

      if (keyEvent.getKeyCode() == KeyEvent.VK_ESCAPE) {
         this.close();
      }
   }
}
