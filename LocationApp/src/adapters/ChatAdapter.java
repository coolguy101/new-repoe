package adapters;

import java.util.ArrayList;
import java.util.List;

import com.example1.locationapp.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


public class ChatAdapter extends ArrayAdapter<String> {
	private static LayoutInflater inflator;
	private Context context;
	private ArrayList<String> chat_list;
	ViewHolder holder = new ViewHolder();
	public ChatAdapter(Context context, int resource,
			List<String> objects){
		super(context,resource,objects);
		this.chat_list = (ArrayList<String>) objects;
		this.context=context;
		System.out.println("ChatAdapter is working");
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		String ChatMessage = chat_list.get(position);
		inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if(ChatMessage.endsWith("   "))
		{
			convertView = inflator.inflate(R.layout.chat_layout_right, parent,false);
			holder.message = (TextView) convertView.findViewById(R.id.textView1);
			holder.message.setText(chat_list.get(position));
		}
		else
		{
			convertView = inflator.inflate(R.layout.chat_layout_left, parent,false);
			holder.message = (TextView) convertView.findViewById(R.id.textView1);
			holder.message.setText(chat_list.get(position));
		}
		return convertView;
	}
	
	public static class ViewHolder{
        public TextView message; 
        public TextView item2;
        public ImageButton imageButton;
    }

}
