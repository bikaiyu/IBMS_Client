package cn.com.yisquare.ibms.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import cn.com.yisquare.ibms.MyApplication;
import cn.com.yisquare.ibms.R;

public class Pause_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pause_remark);
    }
    public void remark_goback(View v){
        this.finish();
    }
    public void remark_send(View v){
        setResult(MyApplication.PAUSE_ACTIVITY);
        this.finish();
    }
}
