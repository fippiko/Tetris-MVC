package game.view.game;

import game.controller.Controller;
import game.controller.GameController;
import game.model.Game;
import game.view.View;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

public class GameView extends View {

   private static final int WIDTH  = 800;
   private static final int HEIGHT = 600;

   private PreviewView      previewView;
   private InformationView  infoView;
   private GameGridView     gridView;
   private GameoverView     gameoverView;

   public GameView(GameController controller) {
      super(controller, WIDTH, HEIGHT);

      this.initialize(controller);
   }

   private void initialize(GameController controller) {
      this.setLayout(new BorderLayout());

      this.previewView = new PreviewView(controller, 200, 350);
      this.infoView = new InformationView(controller, 200, 200);
      this.gridView = new GameGridView(controller, 600, 600, Game.COLCOUNT, Game.ROWCOUNT);
      this.gameoverView = new GameoverView(controller, WIDTH, HEIGHT);
      
      JPanel sidebarPanel = new JPanel(new BorderLayout());
      sidebarPanel.add(previewView, BorderLayout.NORTH);
      sidebarPanel.add(infoView, BorderLayout.CENTER);

      this.add(sidebarPanel, BorderLayout.EAST);
      this.add(gridView, BorderLayout.CENTER);
   }

   public void doGameover() {
      this.removeAll();

      this.add(gameoverView, BorderLayout.CENTER);
   }

   public void updateAttributes(Game game) {
      this.gridView.updateAllForms(game.getAllForms());
      this.previewView.updateNextForm(game.getNextForm());
      this.infoView.updateLevel(game.getLevel());
      this.infoView.updateScore(game.getScore());
      
      if(game.getGameover()){
         this.doGameover();
      }
   }
}
