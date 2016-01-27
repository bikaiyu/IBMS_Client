package cn.com.yisquare.ibms.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import cn.com.yisquare.ibms.R;

/**
 * Created by root on 2016/1/22.
 */
public class DetailActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_range_detail);
    }
    public void goback(View v){
        this.finish();
    }
}
