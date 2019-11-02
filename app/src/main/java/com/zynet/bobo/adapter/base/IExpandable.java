package com.zynet.bobo.adapter.base;

import java.util.List;


/**
 * @author Bobo
 * @date 2019/9/21
 * describe
 */
public interface IExpandable<T> {
    boolean isExpanded();
    void setExpanded(boolean expanded);
    List<T> getSubItems();

    /**
     * Get the level of this item. The level start from 0.
     * If you don't care about the level, just return a negative.
     */
    int getLevel();
}
