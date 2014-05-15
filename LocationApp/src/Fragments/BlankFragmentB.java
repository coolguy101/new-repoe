package Fragments;

import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example1.locationapp.R;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * 
 */
public class BlankFragmentB extends Fragment {
	
	private ListView friendListView;
	private ArrayList<String> friendlist = new ArrayList<String>();
	private ArrayAdapter<String> adapter ;
	public BlankFragmentB() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_blank_fragment_b, container,
				false);
		friendListView = (ListView) view.findViewById(R.id.friendlistview);
		friendlist.add("yazhou1");
		adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,friendlist);
		friendListView.setAdapter(adapter);
		return view;
	}

}
