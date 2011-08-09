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
            this.addNewForm(this.game);
            break;
         case FORMACTIVE :
            this.doMovement(this.game);
            //this.checkFormCollision(this.game);
            break;
      }

      this.getView().updateView(this.game);
   }

   private long getHorizontalSpeedInterval() {
      return Integer.parseInt(Configuration.VERTICALSPEED.getValue());
   }

   private long getVerticalSpeedInterval() {
      String speed = Configuration.SPEED.getValue();

      int verticalInterval = 400 - Integer.parseInt(speed);
      // TODO

      return verticalInterval;
   }

   private void doMovement(Game game) {
      Form activeForm = game.getActiveForm();
      
      //do horizontal movement if left or right button is pressed
      if (this.isKeyPressed(KeyEvent.VK_LEFT) || this.isKeyPressed(KeyEvent.VK_RIGHT)) {
         if (TimeHelper.timeReached(this, "moveHorizontal", this.getHorizontalSpeedInterval())) {      
            int horizontalDelta = this.getHorizontalDelta();
            if (!CollisionHelper.checkHorizontalCollision(activeForm, horizontalDelta, game.getAllForms())) {
               activeForm.moveHorizontal(horizontalDelta);
            }
         }
      }
      
      //do vertical movement on vertical-intervall
      if (TimeHelper.timeReached(this, "moveVertical", this.getVerticalSpeedInterval())) {
         int verticalDelta = this.getVerticalDelta();
         if (!CollisionHelper.checkVerticalCollision(activeForm, verticalDelta, game.getAllForms())) {
            activeForm.moveVertical(verticalDelta);
         }
         else {
            game.setState(GameState.NEXTFORM);
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
      game.addForm(this.generateRandomForm());
   }

   private Form generateRandomForm() {
      int startcol = 5;

      Form randomForm = new FormL(startcol, 0);

      randomForm.setVerticalSpeed(1);

      return randomForm;
   }

   @Override
   public GameView getView() {
      return (GameView) super.getView();
   }

   @Override
   public void keyPressed(KeyEvent keyEvent) {
      super.keyPressed(keyEvent);

      if (keyEvent.getKeyCode() == KeyEvent.VK_ESCAPE) {
         this.close();
      }
   }
}
