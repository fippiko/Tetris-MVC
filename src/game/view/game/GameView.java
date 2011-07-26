package game.view.game;

import game.controller.Controller;
import game.model.Game;
import game.view.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GameView extends View {

   private int width  = 800;
   private int height = 600;
   
   private GameGridView gridView  = null;
   private String fpsString = "";

   public GameView(Controller controller, PreviewView previewView, ScoreView scoreView, GameGridView gridView) {
      super(controller);
      
      this.gridView = gridView;

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
   
   @Override
   protected void paintComponent(Graphics g) {   
      super.paintComponent(g);
      
      Graphics2D g2d = (Graphics2D)g;
      
      g2d.setColor(Color.BLACK);
      g2d.drawString(this.fpsString, 50, 50);
   }

   public void updateView(Game game) {
      this.gridView.updateView(game.getActiveForms());
   }

   public void updateFps(int fps) {
      this.fpsString = Integer.toString(fps);
   }
}
