package game.view;

import game.controller.Controller;
import game.helper.InputHelper;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public abstract class View extends JPanel implements ActionListener {

   public static final int BORDERWIDTH = 2;

   private Controller      controller;

   private int             width;
   private int             height;

   public View(Controller controller, final int width, final int height) {
      this.controller = controller;

      setBorder(BorderFactory.createLineBorder(Color.blue, BORDERWIDTH));

      this.width = width;
      this.height = height;
      this.addKeyListener(InputHelper.getInstance());

      // JPanel uses for default the FlowLayout,
      // because of this, set the vertical gap to 0
      ((FlowLayout) this.getLayout()).setVgap(0);

      // set focusable for catching key-events
      this.setFocusable(true);
   }

   private void addListener(Component comp) {
      if (comp instanceof JButton) {
         ((JButton) comp).addActionListener(this);
         ((JButton) comp).addKeyListener(InputHelper.getInstance());
      }
      else if (comp instanceof JTextField) {
         ((JTextField) comp).addActionListener(this);
         ((JTextField) comp).addKeyListener(InputHelper.getInstance());
      }
      else if (comp instanceof JPanel) {
         for (Component subComp : ((JPanel) comp).getComponents()) {
            this.addListener(subComp);
         }
      }
   }

   @Override
   public void add(Component comp, Object Constraints) {
      this.addListener(comp);
      super.add(comp, Constraints);
   }

   @Override
   public Component add(Component comp) {
      this.addListener(comp);

      super.add(comp);
      this.validate();

      return comp;
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      // Override this method to catch ActionEvents
   }

   protected Controller getController() {
      return this.controller;
   }

   @Override
   public void repaint() {
      super.repaint();
   }

   @Override
   public Dimension getPreferredSize() {
      return new Dimension(this.width, this.height);
   }

   public boolean contains(View view) {
      boolean contains = false;
      for (Component comp : this.getComponents()) {
         if (comp instanceof View) {
            if (comp == view) {
               contains = true;
            }
            else {
               contains = ((View) comp).contains(view);
            }
         }

         if (contains) {
            break;
         }
      }

      return contains;
   }
}
