package game.controller;

import game.enums.ControllerState;
import game.view.MainFrame;
import game.view.MainView;

import javax.swing.JFrame;

public class MainController extends ControllerBase implements Runnable {
   private JFrame                  mainFrame;

   private ControllerBase          activeController;
   private ControllerBase          defaultController;

   private MenuController          menuController;
   private GameController          gameController;
   private ConfigurationController configController;

   public MainController() {
      this(null);
   }

   public MainController(ControllerBase parentController) {
      super(parentController);
   }

   @Override
   protected boolean initialize() {
      this.setView(new MainView(this));

      this.mainFrame = new MainFrame();
      this.mainFrame.add(this.getView());

      this.menuController = new MenuController(this);
      this.configController = new ConfigurationController(this);

      this.defaultController = this.menuController;
      this.setActiveController(this.defaultController);

      return true;
   }

   public void start() {
      Thread t = new Thread(this);
      t.start();
   }

   @Override
   public void updateView() {
      super.updateView();

      if (this.activeController != null) {
         if (!this.getView().contains(this.activeController.getView())) {
            this.getView().removeAll();
            this.getView().add(this.activeController.getView());
         }
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

   public void setActiveController(ControllerBase activeController) {
      this.activeController = activeController;
      this.activeController.setState(ControllerState.ACTIVE);

      this.clearSubController();
      this.addSubcontroller(this.activeController);
   }

   public void startNewGame() {
      this.gameController = new GameController(this);
      this.setActiveController(this.gameController);
   }

   public void continueGame() {
      this.setActiveController(this.gameController);
   }

   public void showConfiguration() {
      this.setActiveController(this.configController);
   }
}
