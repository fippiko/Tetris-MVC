package game.controller;

import game.enums.ControllerState;
import game.enums.MainState;
import game.view.MainFrame;
import game.view.MainView;

import javax.swing.JFrame;

public class MainController extends Controller implements Runnable {
   private JFrame                  mainFrame;

   private Controller              activeController;

   private MenuController          menuController;
   private GameController          gameController;
   private ConfigurationController configController;

   private MainState               currentState;
   private MainState               newState;
   private boolean                 activeControllerChanged;

   public MainController() {
      this(null);
   }

   public MainController(Controller parentController) {
      super(parentController);
   }

   @Override
   protected boolean initialize() {
      this.setView(new MainView(this));

      this.mainFrame = new MainFrame();
      this.mainFrame.add(this.getView());

      this.menuController = new MenuController(this);
      this.configController = new ConfigurationController(this);

      this.setNewState(MainState.MENU);

      return true;
   }

   private void setNewState(MainState newState) {
      this.newState = newState;
   }

   public void start() {
      Thread t = new Thread(this);
      t.start();
   }
   
   public void check() {
      if (this.activeController.getState() == ControllerState.CLOSE) {
         if (this.activeController == this.menuController) {
            this.close();
         }
         else {
            if (this.gameController != null) {
               this.menuController.setGameRunning(true);
            }

            this.setNewState(MainState.MENU);
         }
      }
   }

   @Override
   public void run() {
      while (this.getState() != ControllerState.CLOSE) {

         if (this.currentState != this.newState) {
            this.setActiveController(this.newState);
         }
         
         this.work();
         this.updateView();
         this.repaint();
         this.check();
            
         try {
            Thread.sleep(10);
         } catch (InterruptedException e) {
         }
      }

      System.exit(0);
   }

   public static void main(String[] args) {
      MainController mainController = new MainController();
      mainController.start();
   }
   
   @Override
   public void updateView() {
      super.updateView();
      
      if(this.activeControllerChanged){

         this.getView().removeAll();
         this.getView().add(this.activeController.getView());
         
         this.activeControllerChanged = false;
      }
   }

   public void setActiveController(MainState state) {
      this.currentState = state;

      switch (state) {
         case MENU :
            this.activeController = this.menuController;
            break;
         case NEWGAME :
            this.gameController = new GameController(this);
            this.activeController = this.gameController;
            break;
         case CONTINUEGAME:
            this.activeController = this.gameController;
            break;
         case CONFIGURATION :
            this.activeController = this.configController;
            break;
      }

      this.activeController.setState(ControllerState.ACTIVE);

      this.clearSubController();
      this.addSubcontroller(this.activeController);

      this.activeControllerChanged = true;
   }

   public void startNewGame() {
      this.setNewState(MainState.NEWGAME);
   }

   public void continueGame() {
      this.setNewState(MainState.CONTINUEGAME);
   }

   public void showConfiguration() {
      this.setNewState(MainState.CONFIGURATION);
   }
}
