package com.example1.locationapp;

import java.util.ArrayList;

import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.SmackAndroid;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.filter.MessageTypeFilter;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.Presence;

import com.google.android.gms.drive.internal.x;
import com.google.android.gms.internal.in;

import ChatAdapter.ChatAdapter;
import InternetConnection.IMcontroller;
import Model.Comments;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.gsm.SmsMessage.MessageClass;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class ChatActivity extends Activity {
	private ListView ChatListView;
	//private ArrayAdapter<String> ListViewAdapter;
	private ArrayList<String> ListViewItem;
	private Button button;
	private EditText chatEditText;
	private Context context = this;
	private PacketFilter packFilter = new MessageTypeFilter(Message.Type.chat);
	private Handler handler = new Handler();
	private String LoginUserName;
	private ChatAdapter newChatAdapter;
	public final static String IP = "54.186.214.150";
	public final static int PORT = 5222;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chat);
		LoginUserName=getIntent().getStringExtra("UserName");
		initView(); // must have this line
		MainPage.IMcontrol.loginXMPPserver("yazhou1","123456");
		MainPage.IMcontrol.ThreadSleep(300);
		ListenToIncomingMsg();// must add this line to listen to incoming message
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
					String MyMessage = chatEditText.getText().toString();
					if(!MyMessage.isEmpty())
					{
						MainPage.IMcontrol.sendMessage(MyMessage,"yazhou2@ip-54-186-214-150");// hardcode UserName
						chatEditText.setText(null);
						ListViewItem.add(MyMessage+"   ");
						newChatAdapter.notifyDataSetChanged();
					}
					else
					{
						Toast.makeText(getApplicationContext(), "Nothing to send",Toast.LENGTH_SHORT).show();
					}
			}});
    }
	
	/**
	 * create view from XML file,call this function in onCreate() method
	 */
	private void initView()
	{
		ChatListView = (ListView) findViewById(R.id.listView1);
		ListViewItem = new ArrayList<String>();
		ActionBar bar  = getActionBar();
		
		button = (Button) findViewById(R.id.chatbutton);
		chatEditText = (EditText) findViewById(R.id.chatbox);
		newChatAdapter = new ChatAdapter(getApplicationContext(),R.layout.chat_layout_left, ListViewItem);
		ChatListView.setAdapter(newChatAdapter);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.chat, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	/**
	 * need to call this function in onCreate() method in order to get incoming message
	 */
	private void ListenToIncomingMsg()
	{	
		XMPPConnection xmppConnection =  InternetConnection.IMcontroller.getConnection();
		xmppConnection.addPacketListener(new PacketListener() {
		String message = "";	
			@Override
			public void processPacket(Packet arg0) {
				Message incomingMessage = (Message) arg0;
				if(incomingMessage.getBody()!=null)
				{   message = incomingMessage.getBody().toString();
					ListViewItem.add(message);
					handler.post(new Runnable() {
						@Override
						public void run() {
						newChatAdapter.notifyDataSetChanged();
						}
					});
				}
			}
		},packFilter);
		
	}

}
