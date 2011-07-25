package game.controller;

import game.enums.ConfigurationAttribute;
import game.model.Configuration;
import game.view.ConfigurationView;

import java.awt.event.KeyEvent;

public class ConfigurationController extends Controller {

   public ConfigurationController() {
      this.initialize();
   }

   private void initialize() {
      Configuration.initializeConfiguration();
      
      this.setView(new ConfigurationView(this));
      
      this.getView().setTableData(Configuration.getConfigurationAttributes());

   }

   public void saveConfigurationAttributes() {

   }

   @Override
   public ConfigurationView getView() {
      return (ConfigurationView) super.getView();
   }
}
