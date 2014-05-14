package ChatAdapter;

import Fragments.BlankFragmentA;
import Fragments.BlankFragmentB;
import Fragments.BlankFragmentC;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {

	public ViewPagerAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int arg0) {
		Fragment fragment = new BlankFragmentB();
		if(arg0==0)
		{
			fragment = new BlankFragmentB();
		}
		if(arg0==1)
		{
			fragment = new BlankFragmentB();
		}
		if(arg0==2)
		{
			fragment = new BlankFragmentC();
		}
		return fragment;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 3;
	}

}
