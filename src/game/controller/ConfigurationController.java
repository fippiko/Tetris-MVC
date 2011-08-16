package game.controller;

import game.helper.ConfigurationHelper;
import game.model.Configuration;
import game.view.ConfigurationView;

public class ConfigurationController extends Controller {

   public ConfigurationController() {
      this.initialize();
   }

   private void initialize() {
      this.setView(new ConfigurationView(this, ConfigurationHelper.getInstance()));
   }

   public void saveConfigurationAttributes() {
      Configuration configurationInstance = ConfigurationHelper.getInstance();
      
      configurationInstance.setUsername(this.getView().getUsername());
      configurationInstance.setVerticalSpeed(this.getView().getVerticalSpeed());
      configurationInstance.setHorizontalSpeed(this.getView().getHorizontalSpeed());
      
      ConfigurationHelper.saveConfiguration(configurationInstance);
   }

   @Override
   public ConfigurationView getView() {
      return (ConfigurationView) super.getView();
   }
}
