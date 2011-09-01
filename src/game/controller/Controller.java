package game.controller;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import game.helper.InputHelper;
import game.helper.TimeHelper;
import game.view.View;

public abstract class Controller {
   private View       view;

   private Controller parentController;

   private boolean    close;

   public Controller(Controller parentController) {
      if (parentController != null) {
         this.parentController = parentController;
      }

      this.close = false;
      this.initialize();
   }

   protected abstract boolean initialize();

   public View getView() {
      return this.view;
   }

   public void setView(View view) {
      this.view = view;
   }

   public abstract void updateView();

   public void repaint() {
      if (this.getView() != null) {
         this.getView().repaint();
      }
   }

   public void work() {
      this.handleInput();
   }

   public Controller getParentController() {
      return this.parentController;
   }
   
   public void close(){
      this.close = true;
   }
   
   public boolean isRunning(){
      return !this.close;
   }
   
   protected void handleInput(){
      if(InputHelper.isKeyPressed(KeyEvent.VK_ESCAPE)){
         this.close();
      }
   }
}
