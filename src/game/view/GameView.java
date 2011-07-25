package game.view;

import game.controller.Controller;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

public class GameView extends View {

   private int width  = 800;
   private int height = 600;

   public GameView(Controller controller, PreviewView previewView, ScoreView scoreView, GameGridView gridView) {
      super(controller);

      this.initialize(previewView, scoreView, gridView);
   }

   private void initialize(PreviewView previewView, ScoreView scoreView, GameGridView gridView) {
      this.setPreferredSize(new Dimension(this.width, this.height));
      this.setLayout(new BorderLayout());
      
      JPanel sideBarPanel = new JPanel(new BorderLayout());
      sideBarPanel.add(previewView, BorderLayout.NORTH);
      sideBarPanel.add(scoreView, BorderLayout.CENTER);

      this.add(sideBarPanel, BorderLayout.EAST);
      this.add(gridView, BorderLayout.CENTER);
   }
}
