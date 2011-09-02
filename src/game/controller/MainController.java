package game.controller;

import game.helper.RepeatingReleasedEventsFixer;
import game.view.MainFrame;
import game.view.MainView;

import javax.swing.JFrame;

public class MainController extends Controller implements Runnable {
   private JFrame                  mainFrame;

   private Controller              activeController;
   private Controller              newActiveController;

   private MenuController          menuController;
   private GameController          gameController;
   private ConfigurationController configController;

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

      this.setActiveController(this.menuController);

      new RepeatingReleasedEventsFixer().install();

      return true;
   }

   public void start() {
      Thread t = new Thread(this);
      t.start();
   }

   public void check() {
      if (!this.activeController.isRunning()) {
         if (this.activeController == this.menuController) {
            this.close();
         }
         else {
            if (this.gameController != null) {
               this.menuController.setGameRunning(true);
            }

            this.showMenu();
         }
      }
   }

   @Override
   public void run() {
      while (this.isRunning()) {

         if (this.activeController != this.newActiveController) {
            this.activeController = this.newActiveController;
            this.activeControllerChanged = true;
         }

         this.work();
         this.updateView();
         this.repaint();
         this.check();

         this.activeControllerChanged = false;

         try {
            Thread.sleep(10);
         } catch (InterruptedException e) {
         }
      }

      System.exit(0);
   }

   @Override
   public void work() {
      super.work();

      if (this.activeController != null) {
         this.activeController.work();
      }
   }

   public static void main(String[] args) {
      MainController mainController = new MainController();
      mainController.start();
   }

   @Override
   public void updateView() {

      if (this.activeControllerChanged || this.activeController.getViewChanged()) {
         this.getView().removeAll();
         this.getView().add(this.activeController.getView());
      }

      this.activeController.updateView();
   }

   public void setActiveController(Controller controller) {
      this.newActiveController = controller;
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

   private void showMenu() {
      this.setActiveController(this.menuController);
   }
   
   public void enforceRepaint(){
      this.activeControllerChanged = true;
   }
   
   @Override
   protected void handleInput() {
      return;
   }
}
