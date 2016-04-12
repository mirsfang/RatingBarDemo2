package com.example.mirsfang.ratingbardemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;


import java.math.BigDecimal;

/**
 * Created by Mirs on 2016/4/11.
 *
 * add halfstar show
 *
 * Correction clickEvent from Xml
 */
public class RatingBar extends LinearLayout {
    private boolean mClickable;
    private int starCount;
    private OnRatingChangeListener onRatingChangeListener;
    private float starImageSize;
    private Drawable starEmptyDrawable;
    private Drawable starFillDrawable;
    private Drawable starHalfDrawable;
    //需要改变的地方
    private Changed changed;
    //需要改变的地方
    private float a=0;

    public void setStarHalfDrawable(Drawable starHalfDrawable) {
        this.starHalfDrawable = starHalfDrawable;
    }


    public void setOnRatingChangeListener(OnRatingChangeListener onRatingChangeListener) {
        this.onRatingChangeListener = onRatingChangeListener;
    }

    public void setmClickable(boolean clickable) {
        this.mClickable = clickable;
    }

    public void setStarFillDrawable(Drawable starFillDrawable) {
        this.starFillDrawable = starFillDrawable;
    }

    public void setStarEmptyDrawable(Drawable starEmptyDrawable) {
        this.starEmptyDrawable = starEmptyDrawable;
    }

    public void setStarImageSize(float starImageSize) {
        this.starImageSize = starImageSize;
    }


    /**
     * @param context
     * @param attrs
     */
    public RatingBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(LinearLayout.HORIZONTAL);
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.RatingBar);
        starImageSize = mTypedArray.getDimension(R.styleable.RatingBar_starImageSize, 20);
        starCount = mTypedArray.getInteger(R.styleable.RatingBar_starCount, 5);
        starEmptyDrawable = mTypedArray.getDrawable(R.styleable.RatingBar_starEmpty);
        starFillDrawable = mTypedArray.getDrawable(R.styleable.RatingBar_starFill);
        starHalfDrawable=mTypedArray.getDrawable(R.styleable.RatingBar_starHalf);
        mClickable=mTypedArray.getBoolean(R.styleable.RatingBar_clickable,true);
        for (int i = 0; i < starCount; ++i) {
            ImageView imageView = getStarImageView(context, attrs);
            imageView.setOnClickListener(
                    new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (mClickable) {
                                a+=0.5f;
                                setStar(indexOfChild(v) + a);
                                if (onRatingChangeListener != null) {
                                    onRatingChangeListener.onRatingChange(indexOfChild(v) + a);
                                }
                                if(a==1){
                                    a=0;
                                }
                            }

                        }
                    }
            );
            addView(imageView);
        }
    }

    /**
     * @param context
     * @param attrs
     * @return
     */
    private ImageView getStarImageView(Context context, AttributeSet attrs) {
        ImageView imageView = new ImageView(context);
        ViewGroup.LayoutParams para = new ViewGroup.LayoutParams(
                Math.round(starImageSize),
                Math.round(starImageSize)
        );
        imageView.setLayoutParams(para);
        imageView.setPadding(0, 0, 5, 0);
        imageView.setImageDrawable(starEmptyDrawable);
        imageView.setMaxWidth(10);
        imageView.setMaxHeight(10);
        return imageView;

    }

    /**
     * 设置要改变的数据
     * */
    public void setChanged(Changed changed){
        this.changed=changed;
    }


    public Changed getChanged(){
        return new Changed();
    }
    /**
     * setting start
     *
     * @param starCount
     */

    public void setStar(float starCount) {

        //浮点数的整数部分
        int fint = (int) starCount;
        BigDecimal b1 = new BigDecimal(Float.toString(starCount));
        BigDecimal b2 = new BigDecimal(Integer.toString(fint));
        //浮点数的小数部分
        float fPoint = b1.subtract(b2).floatValue();


        starCount = fint > this.starCount ? this.starCount : fint;
        starCount = starCount < 0 ? 0 : starCount;

        //drawfullstar
        if(changed!=null&&changed.getPosintion()>=0){
            if(b1.floatValue()<=changed.getPosintion()&&changed.changeDrawable!=null){
                for (int i = 0; i < starCount; ++i) {
                    ((ImageView) getChildAt(i)).setImageDrawable(changed.changeDrawable);
                }
            }else{
                for (int i = 0; i < starCount; ++i) {
                    ((ImageView) getChildAt(i)).setImageDrawable(starFillDrawable);
                }
            }
        }else{
            for (int i = 0; i < starCount; ++i) {
                ((ImageView) getChildAt(i)).setImageDrawable(starFillDrawable);
            }
        }

        //drawhalfstar
        if (fPoint > 0) {
            if(changed!=null&&changed.getPosintion()>=0){
                if(b1.floatValue()<=changed.getPosintion()&&changed.changeDrawable!=null) {
                    ((ImageView) getChildAt(fint)).setImageDrawable(changed.changeHarlfDrawable);
                }else {
                    ((ImageView) getChildAt(fint)).setImageDrawable(starHalfDrawable);
                }
            }else {
                ((ImageView) getChildAt(fint)).setImageDrawable(starHalfDrawable);
            }

            //drawemptystar
            for (int i = this.starCount - 1; i >= starCount + 1; --i) {
                ((ImageView) getChildAt(i)).setImageDrawable(starEmptyDrawable);
            }


        } else {
            //drawemptystar
            for (int i = this.starCount - 1; i >= starCount; --i) {
                ((ImageView) getChildAt(i)).setImageDrawable(starEmptyDrawable);
            }

        }


    }

    /**
     * change stat listener
     */
    public interface OnRatingChangeListener {

        void onRatingChange(float RatingCount);

    }

    /**
     * 需要改变的图片的坐标和图片
     * */

    public class Changed{
        private int posintion=-1;
        private Drawable changeDrawable;
        private Drawable changeHarlfDrawable;

        public int getPosintion() {
            return posintion;
        }

        public void setPosintion(int posintion) {
            this.posintion = posintion;
        }

        public Drawable getChangeDrawable() {
            return changeDrawable;
        }

        public void setChangeDrawable(Drawable changeDrawable) {
            this.changeDrawable = changeDrawable;
        }

        public Drawable getChangeHarlfDrawable() {
            return changeHarlfDrawable;
        }

        public void setChangeHarlfDrawable(Drawable changeHarlfDrawable) {
            this.changeHarlfDrawable = changeHarlfDrawable;
        }
    }

}
