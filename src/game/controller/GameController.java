package game.controller;

import game.enums.ConfigurationAttribute;
import game.helper.TimeHelper;
import game.model.Configuration;
import game.model.Game;
import game.model.form.Form;
import game.model.form.FormBlock;
import game.view.game.GameGridView;
import game.view.game.GameView;
import game.view.game.PreviewView;
import game.view.game.ScoreView;

import java.awt.Color;
import java.awt.event.KeyEvent;

public class GameController extends Controller {
   private PreviewController previewController;
   private ScoreController   scoreController;
   private GridController    gridController;

   private Game              game;

   private int               speedController = 0;
   private int               fps;

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

      fps = (int) (1000 / TimeHelper.computeDelta());
      this.getView().updateFps(fps);

      switch (this.game.getState()) {
         case NEXTFORM :
            this.game.addForm(new FormBlock(5, 5));
            break;
         case FORMACTIVE :
            //this.moveForm(this.game.getActiveForm());
            break;
      }

      this.getView().updateView(game);
   }

   private void moveForm(Form form) {
      int nextColumn = form.getNextColumnIndex();
      int nextRow = form.getNextRowIndex();

      this.game.getActiveForm().move(nextColumn, nextRow);

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
