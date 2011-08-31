package game.controller;

import game.enums.ControllerState;
import game.helper.TimeHelper;
import game.view.View;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class ControllerBase {
   private ControllerState           state;
   private boolean                   stateChanged;

   private View                      view;

   private ControllerBase            parentController;

   private ArrayList<ControllerBase> subController = new ArrayList<ControllerBase>();

   private ArrayList<Integer>        pressedKeys   = new ArrayList<Integer>();

   public ControllerBase(ControllerBase parentController) {
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

   protected void addSubcontroller(ControllerBase subController) {
      this.subController.add(subController);
   }

   protected void clearSubController() {
      this.subController.clear();
   }

   protected ArrayList<ControllerBase> getSubController() {
      return this.subController;
   }

   public boolean handleKey(KeyEvent keyEvent) {
      boolean handled = false;
      
      for (ControllerBase subController : this.getSubController()) {
         handled = subController.handleKey(keyEvent);
      }

      int keyCode = keyEvent.getKeyCode();
      if (!this.pressedKeys.contains(keyCode)) {
         this.pressedKeys.add(keyCode);
      }
      
      return handled;
   }

   public void keyReleased(KeyEvent keyEvent) {
      for (ControllerBase subController : this.getSubController()) {
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
      for (ControllerBase subController : this.getSubController()) {
         subController.updateView();
      }
   }

   public void repaint() {
      for (ControllerBase subController : this.getSubController()) {
         subController.repaint();
      }

      if (this.getView() != null) {
         this.getView().repaint();
      }
   }

   public void work() {
      TimeHelper.pushTime(this);

      Iterator<ControllerBase> subControllerItr = this.getSubController().iterator();
      while (subControllerItr.hasNext()) {
         ControllerBase subController = subControllerItr.next();

         if (subController.getState() != ControllerState.UNINITIALIZED) {
            subController.work();
         }
      }
   }

   public void close() {
      this.setState(ControllerState.CLOSE);
   }

   public ControllerBase getParentController() {
      return this.parentController;
   }

}
