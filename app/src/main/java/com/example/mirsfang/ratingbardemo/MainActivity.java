package com.example.mirsfang.ratingbardemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {
    private static final String TAG="MyRatingBar";
    Double a = 1.5;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RatingBar mRatingBar = (RatingBar) findViewById(R.id.ratingbar);

        //点击之后的回调
        mRatingBar.setOnRatingChangeListener(
                new RatingBar.OnRatingChangeListener() {
                    @Override
                    public void onRatingChange(float RatingCount) {
                        Log.e(TAG,RatingCount+"");
                    }
                }
        );

        //优先设置一定数量下的星星样式
        RatingBar.Changed changed=mRatingBar.getChanged();
        changed.setPosintion(2);
        changed.setChangeDrawable(getResources().getDrawable(R.drawable.grabhb_yellow));
        changed.setChangeHarlfDrawable(getResources().getDrawable(R.drawable.grabhb_yellowhalf));
        mRatingBar.setChanged(changed);
        //设置星星
        mRatingBar.setStar(a.floatValue());

        //设置正常下的星星的样式
        mRatingBar.setmClickable(true);
        mRatingBar.setStarImageSize(16f);
        mRatingBar.setStarFillDrawable(getResources().getDrawable(R.drawable.grabhb_red));
        mRatingBar.setStarHalfDrawable(getResources().getDrawable(R.drawable.grabhb_halfred));

    }
}
