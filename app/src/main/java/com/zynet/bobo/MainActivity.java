package com.zynet.bobo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.zynet.bobo.ui.fragment.AboutFragment;
import com.zynet.bobo.ui.fragment.HomeFragment;
import com.zynet.bobo.ui.fragment.MyFragment;
import com.zynet.bobo.ui.widget.TitleView;
import com.zynet.bobo.ui.widget.bottomview.BottomBarItem;
import com.zynet.bobo.ui.widget.bottomview.BottomBarLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Bobo
 * @date 2019/9/21
 * describe
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tvTitle)
    TitleView tvTitle;
    @BindView(R.id.viewpager)
    ViewPager viewPager;

    private List<Fragment> mFragments = new ArrayList<>();

    private RotateAnimation mRotateAnimation;

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    public void initView() {
        BottomBarLayout mBottomBarLayout = findViewById(R.id.bbl);
        tvTitle.setTitle("首页");
        tvTitle.setBackImageGone(true);
        mFragments.add(new HomeFragment());
        mFragments.add(new AboutFragment());
        mFragments.add(new MyFragment());
        mFragments.add(new MyFragment());
       // viewPager.setCurrentItem(2);
        viewPager.setAdapter(new MyFragmentStateAdapter(getSupportFragmentManager()));
        //  viewPager.setOffscreenPageLimit(3);
        mBottomBarLayout.setViewPager(viewPager);
        mBottomBarLayout.setSmoothScroll(true);

        mBottomBarLayout.setOnItemSelectedListener((bottomBarItem, previousPosition, currentPosition) -> {
            Log.i("MainActivity", "position: " + currentPosition);
            if (currentPosition == 0) {
                //如果是第一个，即首页
                if (previousPosition == currentPosition) {
                    //如果是在原来位置上点击,更换首页图标并播放旋转动画
                    if (mRotateAnimation != null && !mRotateAnimation.hasEnded()) {
                        //如果当前动画正在执行
                        return;
                    }

                    bottomBarItem.setSelectedIcon(R.mipmap.tab_loading);//更换成加载图标

                    //播放旋转动画
                    if (mRotateAnimation == null) {
                        mRotateAnimation = new RotateAnimation(0, 360,
                                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                                0.5f);
                        mRotateAnimation.setDuration(800);
                        mRotateAnimation.setRepeatCount(-1);
                    }
                    ImageView bottomImageView = bottomBarItem.getImageView();
                    bottomImageView.setAnimation(mRotateAnimation);
                    bottomImageView.startAnimation(mRotateAnimation);//播放旋转动画

                    //     模拟数据刷新完毕
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            boolean tabNotChanged = mBottomBarLayout.getCurrentItem() == currentPosition; //是否还停留在当前页签
                            bottomBarItem.setSelectedIcon(R.mipmap.tab_home_selected);//更换成首页原来选中图标
                            cancelTabLoading(bottomBarItem);
                        }
                    }, 3000);
                    return;
                }
            }

            //如果点击了其他条目
            BottomBarItem bottomItem = mBottomBarLayout.getBottomItem(0);
            bottomItem.setSelectedIcon(R.mipmap.tab_home_selected);//更换为原来的图标

            cancelTabLoading(bottomItem);//停止旋转动画
        });

        //设置第一个页签的未读数为20
        mBottomBarLayout.setUnread(0, 20);
        //设置第二个页签的未读数
        mBottomBarLayout.setUnread(1, 1001);
        //设置第三个页签显示提示的小红点
        mBottomBarLayout.showNotify(2);
        //设置第四个页签显示NEW提示文字
        mBottomBarLayout.setMsg(3, "NEW");
    }


    /**
     * 停止首页页签的旋转动画
     */
    private void cancelTabLoading(BottomBarItem bottomItem) {
        Animation animation = bottomItem.getImageView().getAnimation();
        if (animation != null) {
            animation.cancel();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    class MyFragmentStateAdapter extends FragmentStatePagerAdapter {

        public MyFragmentStateAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }


}
