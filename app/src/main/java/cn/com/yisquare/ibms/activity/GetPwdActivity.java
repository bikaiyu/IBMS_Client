package cn.com.yisquare.ibms.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import cn.com.yisquare.ibms.R;

public class GetPwdActivity extends Activity {

    protected EditText et_getpwd_username;
    protected Button btn_getpwd_huoquyanzhengma;
    protected EditText et_getpwd_shoudaodeyzm;
    protected Button btn_getpwd_next;
    protected RelativeLayout layout_getpwd_goback;
    protected String shoudaodeyanzhengma;
    protected String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getpwd1);
        initview();
    }

    private void initview() {
        et_getpwd_username = ((EditText) findViewById(R.id.et_getpwd_username));
        btn_getpwd_huoquyanzhengma = ((Button) findViewById(R.id.btn_getpwd_huoquyanzhengma));
        et_getpwd_shoudaodeyzm = ((EditText) findViewById(R.id.et_getpwd_shoudaodeyzm));
        btn_getpwd_next = ((Button) findViewById(R.id.btn_getpwd_next));
        layout_getpwd_goback = ((RelativeLayout) findViewById(R.id.layout_getpwd_goback));
        layout_getpwd_goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetPwdActivity.this.finish();
            }
        });
        btn_getpwd_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = et_getpwd_username.getText().toString();
                shoudaodeyanzhengma = et_getpwd_shoudaodeyzm.getText().toString();
                //校验输入
                //校验验证码
                Intent intent = new Intent(GetPwdActivity.this,GetPwdActivity2.class);
                intent.putExtra("username",username);
                GetPwdActivity.this.startActivity(intent);
            }
        });
    }

}
