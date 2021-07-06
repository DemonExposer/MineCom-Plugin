package nl.ordewittetafel.minecom_plugin.network;

import nl.ordewittetafel.minecom_plugin.Constants;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

public class MessageReceiver {
	public MessageReceiver(JavaPlugin plugin) throws IOException {
		ServerSocket ss = new ServerSocket(plugin.getConfig().getInt(Constants.PLUGIN_PORT_KEY));
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				try {
					Socket s = ss.accept();
					DataInputStream din = new DataInputStream(s.getInputStream());
					byte[] buffer = new byte[1024];
					int read = din.read(buffer);
					din.close();
					s.close();

					JSONObject messageObj = new JSONObject(new String(buffer, 0, read));
					Bukkit.getServer().broadcastMessage("<" + messageObj.getString("sender") + "> " + messageObj.getString("message"));
				} catch (IOException | JSONException e) {
					Bukkit.getServer().broadcastMessage("<Unknown sender> \u00A74Message data lost");
				}
			}
		}, 0, 1);
	}
}
