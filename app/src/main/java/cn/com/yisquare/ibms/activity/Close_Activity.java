package cn.com.yisquare.ibms.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import cn.com.yisquare.ibms.MyApplication;
import cn.com.yisquare.ibms.R;

public class Close_Activity extends AppCompatActivity {

    private TextView tv_close_activity_reason_a;
    private TextView tv_close_activity_reason_b;
    private TextView tv_close_activity_reason_c;
    private TextView tv_close_activity_reason_d;
    private Boolean[] checked = new Boolean[]{false,false,false,false};
    private TextView[] array=new TextView[4];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_close_remark);
        tv_close_activity_reason_a = ((TextView) findViewById(R.id.tv_close_activity_reason_a));
        array[0]=tv_close_activity_reason_a;
        tv_close_activity_reason_b = ((TextView) findViewById(R.id.tv_close_activity_reason_b));
        array[1]=tv_close_activity_reason_b;
        tv_close_activity_reason_c = ((TextView) findViewById(R.id.tv_close_activity_reason_c));
        array[2]=tv_close_activity_reason_c;
        tv_close_activity_reason_d = ((TextView) findViewById(R.id.tv_close_activity_reason_d));
        array[3]=tv_close_activity_reason_d;

        findViewById(R.id.btn_close_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Close_Activity.this, "工单关闭成功!", Toast.LENGTH_SHORT).show();
                setResult(Close_Activity.this.getIntent().getIntExtra("from", MyApplication.CLOSE_ACTIVITY));
                MyApplication.getInstance().getUser().setStatus(0);
                Close_Activity.this.finish();
            }
        });
        tv_close_activity_reason_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checked[0]=!checked[0];
                if(checked[0]){
                    v.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.color_blue_1));
                }else{
                    v.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.white));
                }
            }
        });
        tv_close_activity_reason_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checked[1]=!checked[1];
                if(checked[1]){
                    v.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.color_blue_1));
                }else{
                    v.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.white));
                }
            }
        });
        tv_close_activity_reason_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checked[2]=!checked[2];
                if(checked[2]){
                    v.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.color_blue_1));
                }else{
                    v.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.white));
                }
            }
        });
        tv_close_activity_reason_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checked[3]=!checked[3];
                if(checked[3]){
                    v.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.color_blue_1));
                }else{
                    v.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.white));
                }
            }
        });
        findViewById(R.id.tv_title_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Close_Activity.this, "工单关闭成功!", Toast.LENGTH_SHORT).show();
                setResult(Close_Activity.this.getIntent().getIntExtra("from", MyApplication.CLOSE_ACTIVITY));
                MyApplication.getInstance().getUser().setStatus(0);
                Close_Activity.this.finish();
            }
        });
    }
    public void goback(View view){
        finish();
    }
}
