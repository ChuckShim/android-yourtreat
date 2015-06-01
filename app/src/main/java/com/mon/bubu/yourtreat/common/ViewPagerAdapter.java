package com.mon.bubu.yourtreat.common;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.mon.bubu.yourtreat.R;

/**
 * Created by Chuck on 2015-06-01.
 */
public class ViewPagerAdapter extends PagerAdapter {

    Context context;
    int[] idx_images;
    LayoutInflater inflater;

    public ViewPagerAdapter(Context context, int[] idx_images){
        this.context = context;
        this.idx_images = idx_images;
    }

    @Override
    public boolean isViewFromObject(View view, Object object){
        return view == ((RelativeLayout)object);
    }

    @Override
    public int getCount(){return idx_images.length;}

    @Override
    public Object instantiateItem(ViewGroup container, int position){
        ImageView imageView;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.common_viewpager, container, false);

        imageView = (ImageView) itemView.findViewById(R.id.pagerImageView);
        imageView.setImageResource(idx_images[position]);
//        imageView.setLayoutParams(new RelativeLayout.LayoutParams(800,800));

        ((ViewPager) container).addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object){
        ((ViewPager) container).removeView((RelativeLayout) object);
    }
}
