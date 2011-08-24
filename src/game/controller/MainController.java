package game.controller;

import game.enums.ControllerState;
import game.view.MainFrame;
import game.view.MainView;

import javax.swing.JFrame;

public class MainController extends Controller implements Runnable {
   private JFrame                  mainFrame;

   private Controller              activeController;
   private Controller              defaultController;

   private MenuController          menuController;
   private GameController          gameController;
   private ConfigurationController configController;

   private boolean                 activeControllerChanged;

   public MainController() {
      this.setView(new MainView(this));

      this.mainFrame = new MainFrame();
      this.mainFrame.add(this.getView());

      this.menuController = new MenuController(this);
      this.configController = new ConfigurationController();

      this.defaultController = this.menuController;
      this.setActiveController(this.defaultController);
   }

   public void start() {
      Thread t = new Thread(this);
      t.start();
   }

   @Override
   public void updateView() {
      super.updateView();

      if (this.activeControllerChanged) {
         this.activeControllerChanged = false;

         this.getView().removeAll();
         this.getView().add(this.activeController.getView());
      }
   }

   public void check() {
      if (this.activeController.getState() == ControllerState.CLOSE) {
         if (this.activeController == this.defaultController) {
            this.close();
         }
         else {
            if (this.gameController != null) {
               this.menuController.setGameRunning(true);
            }

            this.setActiveController(this.defaultController);
         }
      }
   }

   @Override
   public void run() {
      while (this.getState() != ControllerState.CLOSE) {

         this.work();
         this.check();
         this.updateView();
         this.repaint();

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

   public void setActiveController(Controller activeController) {
      this.activeController = activeController;
      this.activeController.setState(ControllerState.ACTIVE);

      this.clearSubController();
      this.addSubcontroller(this.activeController);

      this.activeControllerChanged = true;
   }

   public void startNewGame() {
      this.gameController = new GameController();
      this.setActiveController(this.gameController);
   }

   public void continueGame() {
      this.setActiveController(this.gameController);
   }

   public void showConfiguration() {
      this.setActiveController(this.configController);
   }
}
