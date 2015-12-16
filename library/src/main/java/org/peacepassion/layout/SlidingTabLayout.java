package org.peacepassion.layout;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by peacepassion on 15/8/23.
 */
public class SlidingTabLayout extends ViewGroup implements OnPageChangeListener {

    private ViewPager viewPager;
    private List<UnitViewHolder> viewHolders = new ArrayList<>();

    public SlidingTabLayout(Context context) {
        super(context);
    }

    public SlidingTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SlidingTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setUpViewPager(final ViewPager viewPager, List<DataHolder> dataHolders) {
        this.viewPager = viewPager;
        viewPager.addOnPageChangeListener(this);
        for (int i = 0; i < dataHolders.size(); ++i) {
            RelativeLayout v = (RelativeLayout) LayoutInflater.from(getContext()).inflate(R.layout.unit, this, false);
            final int j = i;
            v.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewPager.setCurrentItem(j, false);
                }
            });
            DataHolder dataHolder = dataHolders.get(i);
            TextView tv = (TextView) v.findViewById(R.id.text);
            tv.setText(dataHolder.title);
            TextView tv2 = (TextView) v.findViewById(R.id.text_front);
            tv2.setText(dataHolder.title);
            tv2.setTextColor(dataHolder.titleTargetColor);
            tv2.setAlpha(0);
            ImageView iv = (ImageView) v.findViewById(R.id.image_bg);
            iv.setImageDrawable(dataHolder.back);
            ImageView iv2 = (ImageView) v.findViewById(R.id.image_front);
            iv2.setImageDrawable(dataHolder.front);
            iv2.setAlpha((float) 0);
            viewHolders.add(new UnitViewHolder(tv, tv2, iv, iv2));
            addView(v);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int wSize = MeasureSpec.getSize(widthMeasureSpec);
        int hSize = MeasureSpec.getSize(heightMeasureSpec);
        int count = getChildCount();
        if (count == 0) {
            wSize = 0;
            hSize = getPaddingBottom() + getPaddingTop();
        }
        else {
            int cw = wSize / count;
            measureChildren(MeasureSpec.makeMeasureSpec(cw, MeasureSpec.AT_MOST), MeasureSpec.makeMeasureSpec(hSize, MeasureSpec.AT_MOST));
            hSize = getChildAt(0).getMeasuredHeight() + getPaddingBottom() + getPaddingTop();
        }
        setMeasuredDimension(wSize, hSize);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int count = getChildCount();
        if (count != 0) {
            int t = getPaddingTop(), r = 0, cw = getMeasuredWidth() / count;
            for (int i = 0; i < count; ++i) {
                View child = getChildAt(i);
                int rl = r + (cw - child.getMeasuredWidth()) / 2;
                child.layout(rl, t, rl + child.getMeasuredWidth(), t + child.getMeasuredHeight());
                r += cw;
            }
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        viewHolders.get(position).front.setAlpha(1 - positionOffset);
        viewHolders.get(position).titleFront.setAlpha(1 - positionOffset);
        if (position + 1 < viewHolders.size()) {
            viewHolders.get(position + 1).front.setAlpha(positionOffset);
            viewHolders.get(position + 1).titleFront.setAlpha(positionOffset);
        }
        for (int i = 0; i < position; ++i) {
            viewHolders.get(i).front.setAlpha((float) 0);
            viewHolders.get(i).titleFront.setAlpha(0);
        }
        for (int i = position + 2; i < viewHolders.size(); ++i) {
            viewHolders.get(i).front.setAlpha((float) 0);
            viewHolders.get(i).titleFront.setAlpha(0);
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private static class UnitViewHolder {
        private TextView title;
        private TextView titleFront;
        private ImageView bg;
        private ImageView front;

        public UnitViewHolder(TextView title, TextView titleFront, ImageView bg, ImageView front) {
            this.title = title;
            this.titleFront = titleFront;
            this.bg = bg;
            this.front = front;
        }
    }

}
