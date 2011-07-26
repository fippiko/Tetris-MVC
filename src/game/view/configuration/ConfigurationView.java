package game.view.configuration;

import game.controller.ConfigurationController;
import game.enums.ConfigurationAttribute;
import game.enums.Resources;
import game.helper.ResourceHelper;
import game.model.Configuration;
import game.model.ConfigurationAttributeMap;
import game.view.View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class ConfigurationView extends View {

   private final static int WIDTH          = 400;
   private final static int HEIGHT         = 600;

   private final JButton    backButton     = new JButton(ResourceHelper.getString(Resources.BACK));
   private final JButton    saveButton     = new JButton(ResourceHelper.getString(Resources.SAVE));

   private ConfigurationTable attributeTable = new ConfigurationTable();

   public ConfigurationView(ConfigurationController controller) {
      super(controller);
      this.initialize();
   }

   private void initialize() {
      this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
      this.setLayout(new BorderLayout());

      this.attributeTable.setBounds(BORDERWIDTH, BORDERWIDTH, WIDTH - 2 * BORDERWIDTH, 0);

      this.add(this.backButton);
      this.add(this.saveButton);

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
      }
   }

   @Override
   public void executeKey(KeyEvent keyEvent) {
      super.executeKey(keyEvent);

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

   public void setTableData(ConfigurationAttributeMap configurationAttributes) {
      ConfigurationTableModel attributeTableModel = new ConfigurationTableModel(configurationAttributes.values().size());

      for (ConfigurationAttribute attribute : Configuration.getConfigurationAttributes().keySet()) {
         attributeTableModel.addNewRow(attribute, Configuration.getAttributeValue(attribute));
      }

      this.attributeTable.setModel(attributeTableModel);
      this.add(this.attributeTable.generateScrollPane(), BorderLayout.CENTER);
   }

	public ConfigurationAttributeMap getConfigurationAttributes() {
		return this.attributeTable.getConfigurationAttributes();
	}
}
