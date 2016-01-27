package cn.com.yisquare.ibms.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.com.yisquare.ibms.MyApplication;
import cn.com.yisquare.ibms.R;
import cn.com.yisquare.ibms.bean.User;

public class Reg2Activity extends Activity {

    private RelativeLayout layout_reg2_goback;
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
        setContentView(R.layout.activity_reg2);
        init();
        intent = getIntent();
        user = MyApplication.getInstance().getUser();
        if(intent!=null){
            username = intent.getStringExtra("username");
            if(username!=null && username.length()>6){
                et_reg2_username.setText(username);
                et_reg2_username.setEnabled(false);
            }
        }else{
            et_reg2_username.setEnabled(true);
        }
        /** 显示/隐藏输入的密码*/
        cb_reg2_showpassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                  /* show the password*/
                    et_reg2_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else{
                  /* hide the password */
                    et_reg2_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
        /** 下一步按钮的操作 */
        btn_reg2_next.setOnClickListener(new View.OnClickListener() {
            protected String password;
            protected String phonenumber;
            protected String job;
            protected String name;

            @Override
            public void onClick(View v) {
                name = et_reg2_name.getText().toString();
                job = et_reg2_job.getText().toString();
                phonenumber = et_reg2_phonenumber.getText().toString();
                password = et_reg2_password.getText().toString();
                if(name!=null && name.length()<6&& name.length()<16){
                    user.setName(name);
                    if(job!=null&& job.length()>2&&job.length()<16){
                        user.setPost(job);
                        if(phonenumber!=null && phonenumber.length()>7&&phonenumber.length()<13){
                            user.setPhonenumber(phonenumber);
                            if(password!=null &&password.length()>5&&password.length()<17){
                                user.setPassword(password);
                                /**提交服务器后跳转到成功页*/
                                Intent intent = new Intent(Reg2Activity.this,Reg3Activity.class);
                                Reg2Activity.this.startActivity(intent);
                            }else{
                                //提示密码输入错误
                            }
                        }else{
                            //提示手机号码输入错误
                        }
                    }else{
                        //提示职业输入错误
                    }
                }else{
                    //提示姓名输入错误
                }
            }
        });
        /** 上一步按钮*/
        layout_reg2_goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Reg2Activity.this.finish();
            }
        });

        /** 已有帐号 直接登录*/
        tv_reg2_gologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Reg2Activity.this,LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                Reg2Activity.this.startActivity(intent);
            }
        });
    }

    private void init() {
        layout_reg2_goback = ((RelativeLayout) findViewById(R.id.layout_reg2_goback));//返回按钮
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
