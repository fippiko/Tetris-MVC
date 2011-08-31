package game.controller;

import game.helper.ConfigurationHelper;
import game.model.Configuration;
import game.view.ConfigurationView;

public class ConfigurationController extends ControllerBase {

   public ConfigurationController(ControllerBase parentController) {
      super(parentController);
      
      this.initialize();
   }

   @Override
   protected boolean initialize() {
      this.setView(new ConfigurationView(this, ConfigurationHelper.getConfiguration()));
      
      return true;
   }

   public void saveConfigurationAttributes() {
      Configuration configurationInstance = ConfigurationHelper.getConfiguration();

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
