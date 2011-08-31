package game.view.game;

import game.controller.ControllerBase;
import game.view.View;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

public class GameView extends View {

   private static final int WIDTH    = 800;
   private static final int HEIGHT   = 600;

   public GameView(ControllerBase controller, PreviewView previewView, InformationView scoreView, GameGridView gridView) {
      super(controller);

      this.initialize(previewView, scoreView, gridView);
   }

   private void initialize(PreviewView previewView, InformationView scoreView, GridView gridView) {
      this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
      this.setLayout(new BorderLayout());

      JPanel sidebarPanel = new JPanel(new BorderLayout());
      sidebarPanel.add(previewView, BorderLayout.NORTH);
      sidebarPanel.add(scoreView, BorderLayout.CENTER);

      this.add(sidebarPanel, BorderLayout.EAST);
      this.add(gridView, BorderLayout.CENTER);
   }

   public void doGameover(GameoverView gameoverView) {
      this.removeAll();
      
      this.add(gameoverView, BorderLayout.CENTER);
   }
}
