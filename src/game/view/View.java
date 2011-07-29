package game.view;

import game.controller.Controller;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public abstract class View extends JPanel implements KeyListener, ActionListener {
   public static int  BORDERWIDTH = 2;

   private Controller controller;

   public View(Controller controller) {
      this.controller = controller;

      setBorder(BorderFactory.createLineBorder(Color.blue, BORDERWIDTH));

      // JPanel uses for default the FlowLayout,
      // because of this, set the vertical gap to 0
      ((FlowLayout) this.getLayout()).setVgap(0);

      // set focusable for catching key-events
      this.setFocusable(true);
      this.addKeyListener(this);
   }

   @Override
   public Component add(Component comp) {

      if (comp instanceof JButton) {
         ((JButton) comp).addKeyListener(this);
         ((JButton) comp).addActionListener(this);
      }
      else if (comp instanceof JTextField) {
         ((JTextField) comp).addActionListener(this);
         ((JTextField) comp).addKeyListener(this);
      }

      super.add(comp);
      this.validate();

      return comp;
   }

   @Override
   public void keyPressed(KeyEvent keyEvent) {
      this.getController().keyPressed(keyEvent);
   }

   @Override
   public void keyReleased(KeyEvent keyEvent) {
      this.getController().keyReleased(keyEvent);
   }

   @Override
   public void keyTyped(KeyEvent arg0) {

   }

   @Override
   public void actionPerformed(ActionEvent e) {
      // Override this method to catch ActionEvents
   }

   protected Controller getController() {
      return this.controller;
   }
}
