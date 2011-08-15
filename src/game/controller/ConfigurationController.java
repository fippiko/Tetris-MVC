package game.controller;

import game.helper.ConfigurationHelper;
import game.view.ConfigurationView;

public class ConfigurationController extends Controller {

   public ConfigurationController() {
      this.initialize();
   }

   private void initialize() {
      this.setView(new ConfigurationView(this, ConfigurationHelper.getInstance()));
   }

   public void saveConfigurationAttributes() {
      ConfigurationHelper.saveConfiguration(ConfigurationHelper.getInstance());
   }

   @Override
   public ConfigurationView getView() {
      return (ConfigurationView) super.getView();
   }
}
