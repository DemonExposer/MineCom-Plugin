package nl.ordewittetafel.minecom_plugin.listeners;

import nl.ordewittetafel.minecom_plugin.Constants;
import nl.ordewittetafel.minecom_plugin.network.MessageTransmitter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.JSONObject;

import java.io.IOException;

public class JoinQuitListener implements Listener {
	private final JavaPlugin plugin;
	private final FileConfiguration config;

	public JoinQuitListener(JavaPlugin plugin) {
		this.plugin = plugin;
		config = plugin.getConfig();
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		JSONObject messageData = new JSONObject();
		messageData.put("message", e.getPlayer().getName() + " joined");

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

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		JSONObject messageData = new JSONObject();
		messageData.put("message", e.getPlayer().getName() + " left");

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
