package cn.com.yisquare.ibms.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import cn.com.yisquare.ibms.R;

public class LoginActivity extends Activity {

    private TextView tv_reg;
    private TextView tv_findpassword;
    private EditText et_username;
    private EditText et_password;
    protected String username;
    protected String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        tv_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegActivity.class);
                startActivity(intent);
            }
        });
        tv_findpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,GetPwdActivity.class);
                startActivity(intent);
            }
        });
    }
    protected void init(){
        tv_reg = ((TextView) findViewById(R.id.tv_login_reg));
        tv_findpassword = ((TextView) findViewById(R.id.tv_login_getpwd));
        et_username = ((EditText) findViewById(R.id.et_login_username));
        et_password = ((EditText) findViewById(R.id.et_login_password));
    }
    /** 登录按钮*/
    public void login(View v){
        username = et_username.getText().toString();
        password = et_password.getText().toString();
        if(username!=null&&password!=null){
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
            this.finish();
        }
    }
}
