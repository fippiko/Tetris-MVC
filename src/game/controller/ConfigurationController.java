package game.controller;

import game.helper.SerializationHelper;
import game.model.Configuration;
import game.model.ConfigurationAttributeMap;
import game.view.configuration.ConfigurationView;

public class ConfigurationController extends Controller {

   public ConfigurationController() {
      this.initialize();
   }

   private void initialize() {
      Configuration.initializeConfiguration(loadConfigurationAttributes());

      this.setView(new ConfigurationView(this));

      this.getView().setTableData(Configuration.getConfigurationAttributes());
   }

   public void saveConfigurationAttributes() {
      ConfigurationAttributeMap newAttributes = this.getView().getConfigurationAttributes();
      Configuration.setConfigurationAttributes(newAttributes);

      SerializationHelper.writeObjectToXml(newAttributes, Configuration.FILENAME);
   }

   public ConfigurationAttributeMap loadConfigurationAttributes() {
      ConfigurationAttributeMap newAttributes = null;

      newAttributes = (ConfigurationAttributeMap) SerializationHelper.readObjectFromXml(Configuration.FILENAME);

      return newAttributes;
   }

   @Override
   public ConfigurationView getView() {
      return (ConfigurationView) super.getView();
   }
}
