package management.untitled;

import management.untitled.Managers.ConfigManager;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;

public final class Nightingale extends JavaPlugin {

    private ConfigManager configManager;

    @Override
    public void onEnable() {
        // Instantiate the ConfigManager with this instance of the main class
        configManager = new ConfigManager(this);

        // Load the configs
        configManager.loadConfigs();

        try {
            PluginDescriptionFile object = getDescription();

            Field field = PluginDescriptionFile.class.getDeclaredField("name");
            field.setAccessible(true);
            field.set(object, configManager.getConfig().getString("pluginName"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
