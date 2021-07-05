package nl.ordewittetafel.minecom_plugin.listeners;

import nl.ordewittetafel.minecom_plugin.Constants;
import nl.ordewittetafel.minecom_plugin.network.MessageTransmitter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.JSONObject;

import java.io.IOException;

/**
 * This class waits for a message in the chat and then sends it to the Discord bot connected to this plugin
 */
public class ChatListener implements Listener {
	private final JavaPlugin plugin;
	private final FileConfiguration config;

	public ChatListener(JavaPlugin plugin) {
		this.plugin = plugin;
		config = plugin.getConfig();
	}

	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent e) {
		JSONObject messageData = new JSONObject();
		messageData.put("sender", e.getPlayer().getName());
		messageData.put("message", e.getMessage());

		try {
			MessageTransmitter.transmit(
					config.getString(Constants.DC_BOT_IP_KEY),
					config.getInt(Constants.DC_BOT_PORT_KEY),
					messageData
			);
		} catch (IOException err) {
			err.printStackTrace();
		}
	}
}
