package game.view.game;

import game.controller.GameController;
import game.model.Game;
import game.view.View;

import java.awt.BorderLayout;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GameView extends View {

   private static final int WIDTH    = 800;
   private static final int HEIGHT   = 600;

   private PreviewView      previewView;
   private InformationView  infoView;
   private GameGridView     gridView;
   private GameoverView     gameoverView;
   private PauseView        pauseView;

   private boolean          gameover = false;
   private boolean          paused   = false;

   public GameView(GameController controller) {
      super(controller, WIDTH, HEIGHT);

      this.initialize(controller);
   }

   private void initialize(final GameController controller) {
      this.setLayout(new BorderLayout());

      this.previewView = new PreviewView(controller, 200, 350);
      this.infoView = new InformationView(controller, 200, 200);
      this.gridView = new GameGridView(controller, 600, 600, Game.COLCOUNT, Game.ROWCOUNT);
      this.gameoverView = new GameoverView(controller, WIDTH, HEIGHT);
      this.pauseView = new PauseView(controller, WIDTH, HEIGHT);
   }

   public void updateAttributes(final Game game) {
      this.gameover = game.getGameover();
      this.paused = game.getPaused();
      this.gridView.updateAllForms(game.getAllForms());
      this.previewView.updateNextForm(game.getNextForm());
      this.infoView.updateLevel(game.getLevel());
      this.infoView.updateScore(game.getScore());

      this.updateView();
   }

   private void updateView() {

      if (this.gameover) {
         if (!this.contains(this.gameoverView)) {
            this.removeAll();

            this.add(this.gameoverView, BorderLayout.CENTER);
         }
      }
      else if (this.paused) {
         if (!this.contains(this.pauseView)) {
            this.removeAll();

            this.add(this.pauseView, BorderLayout.CENTER);
         }
      }
      else if (!this.contains(this.gridView)) {
         this.removeAll();

         JPanel sidebarPanel = new JPanel(new BorderLayout());
         sidebarPanel.add(previewView, BorderLayout.NORTH);
         sidebarPanel.add(infoView, BorderLayout.CENTER);

         this.add(sidebarPanel, BorderLayout.EAST);
         this.add(gridView, BorderLayout.CENTER);
      }

      this.revalidate();
   }

   @Override
   protected void paintComponent(Graphics g) {
      super.paintComponent(g);
   }
}
