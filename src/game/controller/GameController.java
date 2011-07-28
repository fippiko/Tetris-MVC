package game.controller;

import game.enums.GameState;
import game.helper.CollisionHelper;
import game.helper.FormHelper;
import game.helper.TimeHelper;
import game.model.Configuration;
import game.model.Game;
import game.model.form.Form;
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
            this.addNewForm(game);
            break;
         case FORMACTIVE :
            if(TimeHelper.timeReached(this, "moveFormVertical", this.getVerticalSpeedInterval())){
               this.moveFormVertical(game);
               
            }
            if(TimeHelper.timeReached(this, "moveFormHorizontal", this.getHorizontalSpeedInterval())){
               this.moveHorizontal(game);
            }
            
            this.checkFormCollision(game);
            break;
      }

      this.getView().updateView(game);
   }

   

   private long getHorizontalSpeedInterval() {
      return 200;
      //TODO
   }

   private long getVerticalSpeedInterval() {
      String speed = Configuration.SPEED.getValue();
      
      int verticalInterval = 400 - Integer.parseInt(speed);
      //TODO
      
      return verticalInterval;
   }

   private void moveHorizontal(Game game) {
      Form activeForm = game.getActiveForm();

      int nextColumn = activeForm.getColumnIndex() + 1;

      activeForm.setColumnIndex(nextColumn);
   }
   
   private void moveFormVertical(Game game) {
      Form activeForm = game.getActiveForm();

      //int nextColumn = FormHelper.calculateNextColumnIndex(activeForm);
      int nextRow = FormHelper.calculateNextRowIndex(activeForm);

      activeForm.setRowIndex(nextRow);
   }

   private void addNewForm(Game game) {
      game.addForm(this.generateRandomForm());
   }

   private Form generateRandomForm() {
      int startcol = 5;

      Form randomForm = new FormL(startcol, 0);

      randomForm.setVerticalSpeed(1);

      return randomForm;
   }

   private void checkFormCollision(Game game) {
      Form activeForm = game.getActiveForm();

      if (CollisionHelper.checkHorizontalCollision(activeForm, game.getActiveForms())) {
         activeForm.setHorizontalSpeed(0);
      }
      if (CollisionHelper.checkVerticalCollision(activeForm, game.getActiveForms())) {
         activeForm.setVerticalSpeed(0);
         game.setState(GameState.NEXTFORM);
      }
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
