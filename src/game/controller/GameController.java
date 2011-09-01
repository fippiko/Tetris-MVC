package game.controller;

import game.controller.gamecontroller.BreakdownHandler;
import game.controller.gamecontroller.GameActionHandler;
import game.controller.gamecontroller.MovementHandler;
import game.enums.GameAction;
import game.helper.CollisionHelper;
import game.helper.ConfigurationHelper;
import game.helper.FormHelper;
import game.helper.InputHelper;
import game.helper.LevelHelper;
import game.helper.MovementHelper;
import game.helper.ScoreHelper;
import game.helper.TimeHelper;
import game.model.FormUnit;
import game.model.Game;
import game.model.form.Form;
import game.view.game.GameView;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;

public class GameController extends Controller {
   private Game                                     game;
   private Hashtable<GameAction, GameActionHandler> actionHandlers;

   public GameController(Controller parentController) {
      super(parentController);
   }

   @Override
   protected boolean initialize() {
      this.setView(new GameView(this));

      this.game = new Game();

      this.actionHandlers = new Hashtable<GameAction, GameActionHandler>();
      this.actionHandlers.put(GameAction.BREAKDOWN, new BreakdownHandler(this));
      this.actionHandlers.put(GameAction.MOVEFORM, new MovementHandler(this));

      return true;
   }

   @Override
   public void work() {
      super.work();

      GameActionHandler handler = this.actionHandlers.get(this.game.getState());

      if (handler != null) {
         handler.work(this.game);
      }
      else {
         switch (this.game.getState()) {
            case NEXTFORM :
               this.addNewForm(this.game);
               this.game.setState(GameAction.MOVEFORM);

               if (this.checkForGameover(this.game)) {
                  this.game.setState(GameAction.GAMEOVER);
               }
               break;
            case MOVEFORM :
               if (this.checkForBreakdown(this.game)) {
                  this.game.setState(GameAction.BREAKDOWN);
               }
               break;
            case INSTANTDOWN :
               this.doInstantDown(game);
               this.game.setState(GameAction.BREAKDOWN);
               break;
            case GAMEOVER :
               this.doGameover(this.game);
               break;
            case NEWGAME :
               this.initialize();
               break;
         }
      }
   }

   private boolean checkForBreakdown(Game game) {
      boolean verticalCollided = false;
      boolean lastChanceUsed = false;

      Form activeForm = game.getActiveForm();

      verticalCollided = !CollisionHelper.checkVerticalCollision(activeForm, 1, game.getDeadForms());

      if (verticalCollided) {
         lastChanceUsed = TimeHelper.timeReached(this, "checkForBreakdown", MovementHelper.getVerticalSpeedInterval(game.getLevel()));
      }

      return verticalCollided && lastChanceUsed;
   }

   private void doGameover(Game game) {
      game.setGameover(true);
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

   private void doInstantDown(Game game) {
      Form activeForm = game.getActiveForm();

      boolean formIsAtBottom = false;
      while (!formIsAtBottom) {
         formIsAtBottom = !FormHelper.moveFormVertical(activeForm, 1, game.getDeadForms());
      }
   }

   public void startNewGame() {
      this.game.setState(GameAction.NEWGAME);
   }

   @Override
   public void updateView() {
      this.getView().updateAttributes(this.game);
   }

   @Override
   protected void handleInput() {
      super.handleInput();

      if (InputHelper.isKeyPressed(KeyEvent.VK_SPACE, true)) {
         if (!this.game.getGameover()) {
            this.rotateForm();
         }
         else {
            this.startNewGame();
         }
      }

      if (InputHelper.isKeyPressed(KeyEvent.VK_F, true)) {
         int currentLevel = this.game.getLevel();
         this.game.setLevel(currentLevel + 1);
      }

      if (InputHelper.isKeyPressed(KeyEvent.VK_ENTER, true)) {
         this.game.setState(GameAction.INSTANTDOWN);
      }

   }

   public Game getGame() {
      return this.game;
   }
}
