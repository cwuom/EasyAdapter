package com.lxj.easyadapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;


/**
 * @param <T>
 */
public abstract class CommonAdapter<T> extends MultiItemTypeAdapter<T> {
    protected int mLayoutId;
    protected List<T> mDatas;

    private HeaderAndFooterWrapper<T> wrapper;

    public CommonAdapter(final int layoutId, List<T> datas) {
        super(datas);
        mLayoutId = layoutId;
        mDatas = datas;

        addItemViewDelegate(new ItemViewDelegate<T>() {
            @Override
            public int getLayoutId() {
                return layoutId;
            }

            @Override
            public boolean isForViewType(@NonNull T item, int position) {
                return true;
            }

            @Override
            public void convert(@NonNull ViewHolder holder, @NonNull T t, int position) {
                CommonAdapter.this.convert(holder, t, position);
            }
        });
    }

    protected abstract void convert(@NonNull ViewHolder holder, @NonNull T t, int position);

    public RecyclerView.Adapter wrapper() {
        return wrapper == null ? this : wrapper;
    }

    public void notifyWrapperDataSetChanged() {
        if (wrapper != null) {
            wrapper.notifyDataSetChanged();
        } else {
            notifyDataSetChanged();
        }
    }

    public void addHeaderView(View headerView) {
        createWrapperIfNeed();
        wrapper.addHeaderView(headerView);
    }

    public void addFooterView(View footerView) {
        createWrapperIfNeed();
        wrapper.addFootView(footerView);
    }

    private void createWrapperIfNeed() {
        if (wrapper == null) {
            wrapper = new HeaderAndFooterWrapper<>(this);
        }
    }
}
