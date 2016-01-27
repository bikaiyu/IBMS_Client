package cn.com.yisquare.ibms.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Toast;

import cn.com.yisquare.ibms.R;


public class AppraiseActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appraise);
        findViewById(R.id.iv_title_option).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AppraiseActivity.this, "提交成功!", Toast.LENGTH_SHORT).show();
                AppraiseActivity.this.finish();
            }
        });
        findViewById(R.id.iv_title_goback).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppraiseActivity.this.finish();
            }
        });

    }

}
