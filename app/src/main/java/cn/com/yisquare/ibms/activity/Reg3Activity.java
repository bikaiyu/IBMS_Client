package cn.com.yisquare.ibms.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.com.yisquare.ibms.R;
import cn.com.yisquare.ibms.bean.User;

public class Reg3Activity extends Activity {

    private RelativeLayout layout_goback;
    private EditText et_reg2_username;
    private Button btn_reg2_next;
    private TextView tv_reg2_gologin;
    private Intent intent;
    private EditText et_reg2_name;
    private EditText et_reg2_job;
    private EditText et_reg2_phonenumber;
    private EditText et_reg2_password;
    private CheckBox cb_reg2_showpassword;
    private User user;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg3);
        init();


    }
    /** 下一步按钮的操作 */
    public void gologin(View v){
        Intent intent = new Intent(Reg3Activity.this,LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    private void init() {
        layout_goback = ((RelativeLayout) findViewById(R.id.layout_reg2_goback));//返回按钮
        et_reg2_username = ((EditText) findViewById(R.id.et_reg2_username));//用户名
        et_reg2_name = ((EditText) findViewById(R.id.et_reg2_name));//姓名
        et_reg2_job = ((EditText) findViewById(R.id.et_reg2_job));//职业
        et_reg2_phonenumber = ((EditText) findViewById(R.id.et_reg2_phonenumber));//手机
        et_reg2_password = ((EditText) findViewById(R.id.et_reg2_password));//密码
        cb_reg2_showpassword = ((CheckBox) findViewById(R.id.cb_reg2_showpassword));//显示密码
        btn_reg2_next = ((Button) findViewById(R.id.btn_reg2_next));//下一步按钮
        tv_reg2_gologin = ((TextView) findViewById(R.id.tv_reg2_gologin));//已有账号,去登陆
    }
}
