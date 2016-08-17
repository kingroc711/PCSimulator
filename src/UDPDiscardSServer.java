import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import net.sf.json.JSONObject;

public class UDPDiscardSServer {
	public static void main(String[] args){
		byte[] buffer = new byte[1024];
		int port = 8091;
		DatagramSocket server = null;
		try {
			server = new DatagramSocket (port);
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
			while(true){
				try {
					server.receive(packet);
					String s = new String(packet.getData(), 0, packet.getLength(), "UTF-8");
					System.out.println("address : " + packet.getAddress() + ", port : " + packet.getPort() + ", content : " + s);
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("method", "update");
					HttpUtils.sendPost("http:/" + packet.getAddress() + ":8092", jsonObject.toString());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}finally{
			if(server != null)
				server.close();
		}
	}
}
