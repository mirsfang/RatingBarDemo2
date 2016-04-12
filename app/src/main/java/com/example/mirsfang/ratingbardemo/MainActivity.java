package com.example.mirsfang.ratingbardemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {
    Double a =1.5;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RatingBar mRatingBar = (RatingBar) findViewById(R.id.ratingbar);


        mRatingBar.setOnRatingChangeListener(
                new RatingBar.OnRatingChangeListener() {
                    @Override
                    public void onRatingChange(float RatingCount) {
                        Log.e("ssss",RatingCount+"");
                    }
                }
        );


        RatingBar.Changed changed=mRatingBar.getChanged();
        changed.setPosintion(2);
        changed.setChangeDrawable(getResources().getDrawable(R.drawable.grabhb_yellow));
        changed.setChangeHarlfDrawable(getResources().getDrawable(R.drawable.grabhb_yellowhalf));
        mRatingBar.setChanged(changed);

        mRatingBar.setStar(a.floatValue());
        mRatingBar.setmClickable(true);
        mRatingBar.setStarImageSize(16f);
        mRatingBar.setStarEmptyDrawable(getResources().getDrawable(R.drawable.grabhb_ash));
        mRatingBar.setStarFillDrawable(getResources().getDrawable(R.drawable.grabhb_red));
        mRatingBar.setStarHalfDrawable(getResources().getDrawable(R.drawable.grabhb_halfred));

    }
}