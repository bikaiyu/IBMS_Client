package cn.com.yisquare.ibms.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class MyFpAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentsList = null;
    public MyFpAdapter(FragmentManager fm) {
        super(fm);
    }

    public MyFpAdapter(FragmentManager fm, List<Fragment> fragmentsList) {
        super(fm);
        this.fragmentsList = fragmentsList;
    }


    @Override
    public Fragment getItem(int arg0) {
        return fragmentsList.get(arg0);
    }

    @Override
    public int getCount() {
        return fragmentsList.size();

    }


}


