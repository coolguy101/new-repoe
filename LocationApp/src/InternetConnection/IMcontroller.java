package InternetConnection;

import java.util.ArrayList;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.SmackAndroid;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.Presence;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.widget.ArrayAdapter;

/**
 * This class is used to send msg, recevied msg, and login to XMPP server
 * @author yazhou
 *
 */
public class IMcontroller {
	private static XMPPConnection connection;
	private static String incomingMessage;
	
	public IMcontroller(Context context)
	{
		SmackAndroid.init(context);// must have this line
		
	}

	/**
	 * send message to another user, that's on the same XMPP server
	 * @param message the message to send
	 * @param toUser send the message to user. you need to provide a full userName, example: "yazhou2@ip-xxx-xxx-xxx-xxx"
	 */
	public void sendMessage(String message,String toUser)
	{
		final Message newMessage = new Message(toUser, Message.Type.chat);
		newMessage.setBody(message);
		new AsyncTask<Void, Void, Void>()
		{
			@Override
			protected Void doInBackground(Void... params) {
				connection.sendPacket(newMessage);
				return null;
			}
			
		}.execute();
	}
	/**
	 * Login to the XMPP server
	 * @param ip xxx.xxx.xxx.xxx
	 * @param port default XMPP port is 5222, but you can use your own port
	 * @param userName your account name
	 * @param password your password
	 */
	public void loginXMPPserver(String ip, int port, final String userName,final String password)
	{
		ConnectionConfiguration config = new ConnectionConfiguration(ip,port);
        connection = new XMPPConnection(config);
        new AsyncTask<Void, Void, Void>()
        {
        @Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			try{
				connection.connect();//connect to server
		        connection.login(userName,password);//perform login
		        Presence presence = new Presence(Presence.Type.available);
		        presence.setStatus("Online");// set status
		        connection.sendPacket(presence);// send status to server
		        }
		        catch(Exception e)
		        {
		        	System.out.println("exception is:"+e.toString());
		        }
			return null;
		}
        }.execute();
	}

	public static XMPPConnection getConnection() {
		return connection;
	}

	
	
}
