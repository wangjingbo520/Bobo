package com.zynet.bobo.adapter.base;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;



/**
 * @author Bobo
 * @date 2019/9/21
 * describe
 */
public class SlideInLeftAnimation implements BaseAnimation {
    @Override
    public Animator[] getAnimators(View view) {
        return new Animator[]{
                ObjectAnimator.ofFloat(view, "translationX", -view.getRootView().getWidth(), 0)
        };
    }
}
