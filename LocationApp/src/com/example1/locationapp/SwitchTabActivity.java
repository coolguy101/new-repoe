package com.example1.locationapp;

import adapters.ViewPagerAdapter;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.view.MenuItem;

public class SwitchTabActivity extends FragmentActivity implements TabListener {
	private ActionBar actionBar;
	private ViewPager viewPager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_switch_tab);
		viewPager = (ViewPager) findViewById(R.id.ViewPager);
		ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
		viewPager.setAdapter(adapter);
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				actionBar.setSelectedNavigationItem(arg0);
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		initView();
	}
	/**
	 * make tabs for this activity
	 */
	private void initView()
	{	
		actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		ActionBar.Tab tab1 = actionBar.newTab();
		tab1.setText("Comments");
		tab1.setTabListener(this);
		ActionBar.Tab tab2 = actionBar.newTab();
		tab2.setText("Chats");
		tab2.setTabListener(this);
		ActionBar.Tab tab3 = actionBar.newTab();
		tab3.setText("Friends");
		tab3.setTabListener(this);
		actionBar.addTab(tab1);
		actionBar.addTab(tab2);
		actionBar.addTab(tab3);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.switch_tab, menu);
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

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		System.out.println("OntableSelected"+tab.getPosition());
		viewPager.setCurrentItem(tab.getPosition(),true);
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		System.out.println("OntableUnselected"+tab.getPosition());
		
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		System.out.println("OntableReselected"+tab.getPosition());
		
	}

}
