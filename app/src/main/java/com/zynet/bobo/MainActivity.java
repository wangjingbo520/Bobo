package com.zynet.bobo;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.zynet.bobo.base.BaseMvpActivity;
import com.zynet.bobo.base.BaseMvpPresenter;
import com.zynet.bobo.ui.fragment.AboutFragment;
import com.zynet.bobo.ui.fragment.HomeFragment;
import com.zynet.bobo.ui.fragment.MyFragment;
import com.zynet.bobo.ui.widget.TitleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @author Bobo
 * @date 2019/9/21
 * describe
 */
public class MainActivity extends BaseMvpActivity {

    @BindView(R.id.tvTitle)
    TitleView tvTitle;
    @BindView(R.id.viewpager)
    ViewPager2 viewPager;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.tv3)
    TextView tv3;

    private List<Fragment> mFragments = new ArrayList<>();


    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        tvTitle.setTitle("首页");
        tvTitle.setBackImageGone(true);
        mFragments.add(HomeFragment.newInstance());
        mFragments.add(AboutFragment.newInstance());
        mFragments.add(MyFragment.newInstance());
        //viewPager.setUserInputEnabled(false);
        viewPager.setCurrentItem(0);
        viewPager.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getItemCount() {
                return 3;
            }
        });

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @OnClick({R.id.tv1, R.id.tv2, R.id.tv3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv1:
                viewPager.setCurrentItem(0);
                break;
            case R.id.tv2:
                viewPager.setCurrentItem(1);
                break;
            case R.id.tv3:
                viewPager.setCurrentItem(2);
                break;
            default:
                break;
        }
    }

    @Override
    protected BaseMvpPresenter createPresenter() {
        return null;
    }
}
