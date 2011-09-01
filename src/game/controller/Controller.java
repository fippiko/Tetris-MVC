package game.controller;

import game.enums.ControllerState;
import game.helper.TimeHelper;
import game.view.View;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class Controller {
   private ControllerState           state;
   private boolean                   stateChanged;

   private View                      view;

   private Controller            parentController;

   private ArrayList<Controller> subController = new ArrayList<Controller>();

   private ArrayList<Integer>        pressedKeys   = new ArrayList<Integer>();

   public Controller(Controller parentController) {
      if (parentController != null) {
         parentController.addSubcontroller(this);

         this.parentController = parentController;
      }

      this.setState(ControllerState.UNINITIALIZED);

      if (this.initialize()) {
         this.setState(ControllerState.INITIALIZED);
      }
   }

   protected abstract boolean initialize();

   public ControllerState getState() {
      return this.state;
   }

   protected void setState(ControllerState state) {
      this.state = state;
      this.stateChanged = true;
   }

   public boolean getStateChanged() {
      return this.stateChanged;
   }

   public void resetStateChanged() {
      this.stateChanged = false;
   }

   public View getView() {
      return this.view;
   }

   public void setView(View view) {
      this.view = view;
   }

   protected void addSubcontroller(Controller subController) {
      this.subController.add(subController);
   }

   protected void clearSubController() {
      this.subController.clear();
   }

   protected ArrayList<Controller> getSubController() {
      return this.subController;
   }

   public void handleKey(KeyEvent keyEvent) {
      for (Controller subController : this.getSubController()) {
         subController.handleKey(keyEvent);
      }

      int keyCode = keyEvent.getKeyCode();
      if (!this.pressedKeys.contains(keyCode)) {
         this.pressedKeys.add(keyCode);
      }
   }

   public void keyReleased(KeyEvent keyEvent) {
      Iterator<Controller> controllerIterator = this.getSubController().iterator();
      while (controllerIterator.hasNext()) {
         controllerIterator.next().keyReleased(keyEvent);
      }

      int keyCode = keyEvent.getKeyCode();
      if (this.pressedKeys.contains(keyCode)) {
         this.pressedKeys.remove((Object) keyCode);
      }
   }

   protected boolean isKeyPressed(int keyCode) {
      return this.pressedKeys.contains(keyCode);
   }

   public void updateView() {
      for (Controller subController : this.getSubController()) {
         subController.updateView();
      }
   }

   public void repaint() {
      for (Controller subController : this.getSubController()) {
         subController.repaint();
      }

      if (this.getView() != null) {
         this.getView().repaint();
      }
   }

   public void work() {
      TimeHelper.pushTime(this);

      Iterator<Controller> subControllerItr = this.getSubController().iterator();
      while (subControllerItr.hasNext()) {
         Controller subController = subControllerItr.next();

         if (subController.getState() != ControllerState.UNINITIALIZED) {
            subController.work();
         }
      }
      
      this.handlePressedKeys(this.pressedKeys);
   }

   protected abstract void handlePressedKeys(ArrayList<Integer> pressedKeys);

   public void close() {
      this.setState(ControllerState.CLOSE);
   }

   public Controller getParentController() {
      return this.parentController;
   }

}
