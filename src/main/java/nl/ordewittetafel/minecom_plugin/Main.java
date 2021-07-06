package nl.ordewittetafel.minecom_plugin;

import nl.ordewittetafel.minecom_plugin.listeners.ChatListener;
import nl.ordewittetafel.minecom_plugin.listeners.JoinQuitListener;
import nl.ordewittetafel.minecom_plugin.network.MessageReceiver;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

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
		config.addDefault(Constants.PLUGIN_PORT_KEY, 501); // The port of this plugin
		config.options().copyDefaults(true);
		saveConfig();

		Bukkit.getPluginManager().registerEvents(new JoinQuitListener(this), this);
		Bukkit.getPluginManager().registerEvents(new ChatListener(this), this);

		try {
			new MessageReceiver(this);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("If this error shows up, it means you cannot receive any messages from the Discord bot, because the ServerSocket could not be created");
		}
	}
}
