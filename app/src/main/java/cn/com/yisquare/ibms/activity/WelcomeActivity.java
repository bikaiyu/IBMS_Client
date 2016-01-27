package cn.com.yisquare.ibms.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import cn.com.yisquare.ibms.R;

public class WelcomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         /*去掉APP标题栏*/
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        /*全屏模式*/
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(WelcomeActivity.this,HomeActivity.class);
                startActivity(intent);
                WelcomeActivity.this.finish();
            }
        }).start();
    }
}
