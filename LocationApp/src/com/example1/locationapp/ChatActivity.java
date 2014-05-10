package com.example1.locationapp;

import java.util.ArrayList;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.SmackAndroid;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.MessageTypeFilter;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smackx.receipts.DeliveryReceiptRequest;

import Model.Comments;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class ChatActivity extends Activity {
	private Comments comment;
	private ListView ChatListView;
	private ArrayAdapter<String> ListViewAdapter;
	private ArrayList<String> ListViewItem;
	private XMPPConnection connection;
	private Button button;
	private ChatManager chatManager;
	private EditText chatEditText;
	private Context context = this;
	private PacketFilter packFilter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chat);
		comment = (Comments) getIntent().getSerializableExtra("comments");
		ChatListView = (ListView) findViewById(R.id.listView1);
		ListViewItem = new ArrayList<String>();
		ListViewAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,ListViewItem);
		ChatListView.setAdapter(ListViewAdapter);
		button = (Button) findViewById(R.id.chatbutton);
		SmackAndroid.init(context);
		ConnectionConfiguration config = new ConnectionConfiguration("54.186.214.150", 5222);
        connection = new XMPPConnection(config);
        connection.DEBUG_ENABLED = true;
        chatManager = connection.getChatManager();
        chatEditText = (EditText) findViewById(R.id.chatbox);
        ActionBar bar = getActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#ffFEBB31"));
        bar.setBackgroundDrawable(colorDrawable);
        bar.setHomeButtonEnabled(true);
        packFilter = new MessageTypeFilter(Message.Type.chat);
        button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

					SmackAndroid.init(context);
					final Message newMessage = new Message("yazhou2@54.186.214.150", Message.Type.normal);
					newMessage.setBody("omgomg");
					newMessage.addBody("wocao", "hehe");
					new AsyncTask<Void, Void, Void>(){

						@Override
						protected Void doInBackground(Void... params) {
							connection.sendPacket(newMessage);
							return null;
						}}.execute();
					System.out.println("button pressed");}
	
		});
        new AsyncTask<Void, Void, Void>()
        {
        
		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			try{
				connection.connect();
		        connection.login("yazhou1","123456");
		        Presence presence = new Presence(Presence.Type.available);
		        presence.setStatus("chat with me");
		        connection.sendPacket(presence);
		        }
		        catch(Exception e)
		        {
		        	System.out.println("exception is:"+e.toString());
		        }
			return null;
		}
        }.execute();
        
        
			
		
        
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

	
}
