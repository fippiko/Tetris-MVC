package game.view;

import game.controller.ConfigurationController;
import game.enums.ConfigurationAttribute;
import game.model.Configuration;
import game.view.element.ConfigurationTableModel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.LinkedHashMap;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import resources.ResourceManager;
import resources.Resources;

public class ConfigurationView extends View {

   private final static int WIDTH          = 400;
   private final static int HEIGHT         = 600;

   private final static int ROWHEIGHT      = 40;

   private final JButton    backButton     = new JButton(ResourceManager.getString(Resources.BACK));
   private final JButton    saveButton     = new JButton(ResourceManager.getString(Resources.SAVE));

   private JTable           attributeTable = new JTable(20, 2);

   public ConfigurationView(ConfigurationController controller) {
      super(controller);
      this.initialize();
   }

   private void initialize() {
      this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
      this.setLayout(new BorderLayout());

      this.attributeTable.setBounds(BORDERWIDTH, BORDERWIDTH, WIDTH - 2 * BORDERWIDTH, 0); 
      this.attributeTable.setRowHeight(ROWHEIGHT);
      this.attributeTable.getTableHeader().setSize(this.getWidth(), 15);
      this.attributeTable.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
      this.add(this.attributeTable.getTableHeader(), BorderLayout.NORTH);
      this.add(this.attributeTable, BorderLayout.CENTER);

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

   public void setTableData(LinkedHashMap<ConfigurationAttribute, String> configurationAttributes) {
      ConfigurationTableModel attributeTableModel = new ConfigurationTableModel(configurationAttributes.values().size());

      for (ConfigurationAttribute attribute : Configuration.getConfigurationAttributes().keySet()) {
         attributeTableModel.addNewRow(attribute, Configuration.getAttributeValue(attribute));
      }

      this.attributeTable.setModel(attributeTableModel);
      int headerHeight = this.attributeTable.getTableHeader().getHeight();
      this.attributeTable.setBounds(BORDERWIDTH, headerHeight, this.attributeTable.getWidth(), configurationAttributes.values().size() * ROWHEIGHT);
   }
}
