package game.controller;

import game.enums.ControllerState;
import game.helper.TimeHelper;
import game.view.View;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public abstract class Controller {
   private ControllerState       state;
   private Boolean               stateChanged;

   private View                  view;

   private ArrayList<Controller> subController          = new ArrayList<Controller>();

   private ArrayList<Integer>    pressedKeys            = new ArrayList<Integer>();

   private final static int      DEFAULT_INTERVAL_IN_MS = 200;

   public Controller() {
      this.setState(ControllerState.UNINITIALIZED);
   }

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

   public void keyPressed(KeyEvent keyEvent) {
      for (Controller subController : this.getSubController()) {
         subController.keyPressed(keyEvent);
      }

      int keyCode = keyEvent.getKeyCode();
      if (!this.pressedKeys.contains(keyCode)) {
         this.pressedKeys.add(keyCode);
      }
   }

   public void keyReleased(KeyEvent keyEvent) {
      for (Controller subController : this.getSubController()) {
         subController.keyReleased(keyEvent);
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

      for (Controller subController : this.getSubController()) {
         subController.work();
      }
   }

   public void close() {
      this.setState(ControllerState.CLOSE);
   }

}
