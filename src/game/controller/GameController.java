package game.controller;

import game.controller.handler.game.BreakdownHandler;
import game.controller.handler.game.CheckBreakdownHandler;
import game.controller.handler.game.CheckGameoverHandler;
import game.controller.handler.game.GameActionHandler;
import game.controller.handler.game.GameoverHandler;
import game.controller.handler.game.InstantDownHandler;
import game.controller.handler.game.MovementHandler;
import game.controller.handler.game.NewGameHandler;
import game.controller.handler.game.NextFormHandler;
import game.controller.handler.game.RotateFormHandler;
import game.enums.GameAction;
import game.helper.InputHelper;
import game.model.Game;
import game.view.game.GameView;

import java.awt.event.KeyEvent;
import java.util.Hashtable;


public class GameController extends Controller {
   private Game                                     game;
   private Hashtable<GameAction, GameActionHandler> actionHandlers;

   public GameController(Controller parentController) {
      super(parentController);
   }

   @Override
   public boolean initialize() {
      this.setView(new GameView(this));

      this.game = new Game();

      this.actionHandlers = new Hashtable<GameAction, GameActionHandler>();
      this.actionHandlers.put(GameAction.BREAKDOWN, new BreakdownHandler(this));
      this.actionHandlers.put(GameAction.MOVEFORM, new MovementHandler(this));
      this.actionHandlers.put(GameAction.INSTANTDOWN, new InstantDownHandler(this));
      this.actionHandlers.put(GameAction.CHECKBREAKDOWN, new CheckBreakdownHandler(this));
      this.actionHandlers.put(GameAction.GAMEOVER, new GameoverHandler(this));
      this.actionHandlers.put(GameAction.NEXTFORM, new NextFormHandler(this));
      this.actionHandlers.put(GameAction.CHECKGAMEOVER, new CheckGameoverHandler(this));
      this.actionHandlers.put(GameAction.NEWGAME, new NewGameHandler(this));
      this.actionHandlers.put(GameAction.ROTATEFORM, new RotateFormHandler(this));
      return true;
   }

   @Override
   public void work() {
      super.work();

      GameAction currentAction = this.game.getState();
      GameActionHandler handler = this.actionHandlers.get(currentAction);

      if (handler != null) {
         handler.work(this.game);
      }
      else {
         // TODO throw exception here...
      }
   }

   @Override
   public GameView getView() {
      return (GameView) super.getView();
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
            game.setState(GameAction.ROTATEFORM);
         }
         else {
            game.setState(GameAction.NEWGAME);
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
