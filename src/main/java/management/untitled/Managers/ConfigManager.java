package management.untitled.Managers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import management.untitled.Nightingale;

public class ConfigManager {
    private Plugin plugin;

    private YamlConfiguration config;
    private YamlConfiguration punishmentsModule;
    private YamlConfiguration ranksModule;
    private YamlConfiguration websiteModule;

    public ConfigManager(Nightingale plugin) {
        this.plugin = plugin;
    }

    public void loadConfigs() {
        // Load config.yml
        File configFile = new File(plugin.getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            plugin.saveResource("config.yml", false);
        }
        YamlConfiguration config = new YamlConfiguration();
        try {
            config.load(new InputStreamReader(new FileInputStream(configFile), StandardCharsets.UTF_8));
            this.config = config;
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }

        // Load other config files
        File[] configFiles = {
                new File(plugin.getDataFolder(), "punishmentsmodule.yml"),
                new File(plugin.getDataFolder(), "ranksmodule.yml"),
                new File(plugin.getDataFolder(), "websitemodule.yml")
        };
        for (File file : configFiles) {
            if (!file.exists()) {
                plugin.saveResource(file.getName(), false);
            }
            YamlConfiguration yamlConfig = new YamlConfiguration();
            try {
                yamlConfig.load(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
                if (file.getName().equals("punishmentsmodule.yml")) {
                    punishmentsModule = yamlConfig;
                } else if (file.getName().equals("ranksmodule.yml")) {
                    ranksModule = yamlConfig;
                } else if (file.getName().equals("websitemodule.yml")) {
                    websiteModule = yamlConfig;
                }
            } catch (IOException | InvalidConfigurationException e) {
                e.printStackTrace();
            }
        }
    }


    public YamlConfiguration getConfig() {
        return config;
    }

    public YamlConfiguration getPunishmentsModule() {
        return punishmentsModule;
    }

    public YamlConfiguration getRanksModule() {
        return ranksModule;
    }

    public YamlConfiguration getWebsiteModule() {
        return websiteModule;
    }
}