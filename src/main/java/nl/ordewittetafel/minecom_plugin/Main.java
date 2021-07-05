package nl.ordewittetafel.minecom_plugin;

import nl.ordewittetafel.minecom_plugin.listeners.ChatListener;
import nl.ordewittetafel.minecom_plugin.listeners.JoinQuitListener;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	@Override
	public void onEnable() {
		FileConfiguration config = getConfig();
		/*
		 * The next config values may be changed according to your Discord bot settings in config.yml after the plugin
		 * has been run once
		 */
		config.addDefault(Constants.DC_BOT_IP_KEY, "127.0.0.1"); // The address of the Discord bot
		config.addDefault(Constants.DC_BOT_PORT_KEY, 500); // The port of the Discord bot
		config.options().copyDefaults(true);
		saveConfig();

		Bukkit.getPluginManager().registerEvents(new JoinQuitListener(this), this);
		Bukkit.getPluginManager().registerEvents(new ChatListener(this), this);
	}
}
