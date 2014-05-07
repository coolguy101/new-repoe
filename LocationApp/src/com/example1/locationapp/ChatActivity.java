package com.example1.locationapp;

import java.util.ArrayList;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.SmackAndroid;
import org.jivesoftware.smack.XMPPConnection;

import Model.Comments;
import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ChatActivity extends Activity {
	private Comments comment;
	private ListView ChatListView;
	private ArrayAdapter<String> ListViewAdapter;
	private ArrayList<String> ListViewItem;
	private XMPPConnection connection;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chat);
		comment = (Comments) getIntent().getSerializableExtra("comments");
		ChatListView = (ListView) findViewById(R.id.listView1);
		ListViewItem = new ArrayList<String>();
		ListViewAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,ListViewItem);
		ChatListView.setAdapter(ListViewAdapter);
		Context context = this;
		SmackAndroid.init(context);
		ConnectionConfiguration config = new ConnectionConfiguration("54.186.214.150", 5222);
        connection = new XMPPConnection(config);
        new AsyncTask<Void, Void, Void>()
        {
        
		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			try{
		        connection.connect();
		        connection.login("yazhou1","123456");
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
