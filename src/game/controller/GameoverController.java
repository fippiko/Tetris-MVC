package game.controller;

import game.view.game.GameoverView;

import java.awt.event.KeyEvent;

public class GameoverController extends Controller {
   public GameoverController(Controller parentController) {
      super(parentController);
   }
   
   @Override
   protected boolean initialize() {
      this.setView(new GameoverView(this));
      
      return true;
   }
   
   @Override
   public GameoverView getView() {
      return (GameoverView)super.getView();
   }
   
   @Override
   public void keyPressed(KeyEvent keyEvent) {
      super.keyPressed(keyEvent);
      
      if(keyEvent.getKeyCode() == KeyEvent.VK_SPACE){
         
      }
   }
}
