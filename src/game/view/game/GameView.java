package game.view.game;

import game.controller.Controller;
import game.model.Game;
import game.view.View;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

public class GameView extends View {

   private static final int WIDTH    = 800;
   private static final int HEIGHT   = 600;

   private GameGridView             gridView = null;

   public GameView(Controller controller, PreviewView previewView, ScoreView scoreView, GameGridView gridView) {
      super(controller);

      this.gridView = gridView;

      this.initialize(previewView, scoreView, gridView);
   }

   private void initialize(PreviewView previewView, ScoreView scoreView, GameGridView gridView) {
      this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
      this.setLayout(new BorderLayout());

      JPanel sideBarPanel = new JPanel(new BorderLayout());
      sideBarPanel.add(previewView, BorderLayout.NORTH);
      sideBarPanel.add(scoreView, BorderLayout.CENTER);

      this.add(sideBarPanel, BorderLayout.EAST);
      this.add(gridView, BorderLayout.CENTER);
   }

   public void updateView(Game game) {
      this.gridView.updateView(game);
   }
}
