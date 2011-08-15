package game.view;

import game.controller.ConfigurationController;
import game.enums.Resources;
import game.helper.ResourceHelper;
import game.model.Configuration;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ConfigurationView extends View {

   private static final int WIDTH                = 400;
   private static final int HEIGHT               = 600;

   private final JButton    backButton           = new JButton(ResourceHelper.getString(Resources.BACK));
   private final JButton    saveButton           = new JButton(ResourceHelper.getString(Resources.SAVE));

   // private ConfigurationTable attributeTable = new ConfigurationTable();

   private final JLabel     nameLabel            = new JLabel("Name");
   private final JTextField nameEdit             = new JTextField();

   private final JLabel     verticalSpeedLabel   = new JLabel("Vertical Speed");
   private final JTextField verticalSpeedEdit    = new JTextField();

   private final JLabel     horizontalSpeedLabel = new JLabel("Horizontal Speed");
   private final JTextField horizontalSpeedEdit  = new JTextField();

   public ConfigurationView(ConfigurationController controller, Configuration configuration) {
      super(controller);
      this.initialize(configuration);
   }

   private void initialize(Configuration configuration) {
      this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
      this.setLayout(new BorderLayout());

      //initialize elements
      this.nameEdit.setText(configuration.getName());
      this.verticalSpeedEdit.setText(String.valueOf(configuration.getVerticalSpeed()));
      this.horizontalSpeedEdit.setText(String.valueOf(configuration.getHorizontalSpeed()));

      //add elements to panel
      final JPanel elementPanel = new JPanel(new GridLayout(3, 2));
      elementPanel.add(nameLabel);
      elementPanel.add(nameEdit);
      elementPanel.add(verticalSpeedLabel);
      elementPanel.add(verticalSpeedEdit);
      elementPanel.add(horizontalSpeedLabel);
      elementPanel.add(horizontalSpeedEdit);
      this.add(elementPanel, BorderLayout.CENTER);
      

      final JPanel controlPanel = new JPanel(new BorderLayout());
      controlPanel.add(this.backButton, BorderLayout.WEST);
      controlPanel.add(this.saveButton, BorderLayout.EAST);
      this.add(controlPanel, BorderLayout.SOUTH);
   }

   @Override
   public ConfigurationController getController() {
      return (ConfigurationController) super.getController();
   }

   @Override
   public void actionPerformed(final ActionEvent e) {
      if (e.getSource() == this.backButton) {
         this.close();
      }
      else if (e.getSource() == this.saveButton) {
         this.getController().saveConfigurationAttributes();
         this.getController().close();
      }
   }

   @Override
   public void keyPressed(KeyEvent keyEvent) {
      super.keyPressed(keyEvent);

      if (keyEvent.getKeyCode() == KeyEvent.VK_ESCAPE) {
         this.close();
      }
   }

   public void close() {
      int answer = JOptionPane.showConfirmDialog(this, Resources.CONFIRMBACK_BODY.getString(), Resources.CONFIRMBACK_HEADER.getString(), JOptionPane.YES_NO_OPTION);

      if (answer == JOptionPane.YES_OPTION) {
         this.getController().close();
      }
   }
}
