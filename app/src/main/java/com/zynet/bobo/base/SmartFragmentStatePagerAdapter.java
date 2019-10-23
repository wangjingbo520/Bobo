package com.zynet.bobo.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;


/**
 * @author Bobo
 * @date 2019/9/21
 * describe
 */
public abstract class SmartFragmentStatePagerAdapter extends FragmentStatePagerAdapter {
    /**
     * Sparse array to keep track of registered fragments in memory
     */
    private SparseArray<Fragment> registeredFragments = new SparseArray<>();


    public SmartFragmentStatePagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }


    /**
     * Register the fragment when the item is instantiated
     *
     * @param container
     * @param position
     * @return
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        registeredFragments.put(position, fragment);
        return fragment;
    }


    /**
     * Unregister when the item is inactive
     *
     * @param container
     * @param position
     * @param object
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        registeredFragments.remove(position);
        super.destroyItem(container, position, object);
    }

    /**
     * Returns the fragment for the position (if instantiated)
     *
     * @param position
     * @return
     */
    public Fragment getRegisteredFragment(int position) {
        return registeredFragments.get(position);
    }
}
