package cn.com.yisquare.ibms.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Toast;

import cn.com.yisquare.ibms.R;


public class CreateOrderActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creat_order);
        findViewById(R.id.iv_title_option).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CreateOrderActivity.this, "提交成功!", Toast.LENGTH_SHORT).show();
                CreateOrderActivity.this.finish();
            }
        });

    }

}
