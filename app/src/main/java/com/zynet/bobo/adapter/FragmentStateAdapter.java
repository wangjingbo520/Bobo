package com.zynet.bobo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.zynet.bobo.base.SmartFragmentStatePagerAdapter;

import java.util.List;

/**
 * @author Bobo
 * @date 2019/10/28 0028
 * describe
 */
public class FragmentStateAdapter extends SmartFragmentStatePagerAdapter {
    private List<Fragment> fragmentList;

    public FragmentStateAdapter(FragmentManager fragmentManager, List<Fragment> fragmentList) {
        super(fragmentManager);
        this.fragmentList = fragmentList;
    }


    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}