package nl.ordewittetafel.minecom_plugin.network;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class MessageTransmitter {
	/**
	 * Transmits a message over TCP
	 * @param address The host to send the message to
	 * @param port The host's port
	 * @param message The message to send
	 * @throws IOException If a socket could not be created
	 */
	public static void transmit(String address, int port, JSONObject message) throws IOException {
		Socket s = new Socket(address, port);
		DataOutputStream dout = new DataOutputStream(s.getOutputStream());
		dout.writeBytes(message.toString());
		dout.flush();
		dout.close();
		s.close();
	}
}
