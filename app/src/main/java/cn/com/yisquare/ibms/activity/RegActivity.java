package cn.com.yisquare.ibms.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.com.yisquare.ibms.MyApplication;
import cn.com.yisquare.ibms.R;

public class RegActivity extends Activity {

    private RelativeLayout layout_goback;
    private EditText et_reg_username;
    private EditText et_reg_yanzhengma;
    private TextView tv_reg_get_new_yzm;
    private Button btn_reg_get_yzm;
    private EditText et_reg_shoudaodeyzm;
    private Button btn_reg_next;
    private TextView tv_reg_gologin;
    private String username,yzm,shoudaodeyzm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        init();
        /**上一步按钮 */
        layout_goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegActivity.this.finish();
            }
        });
        /** 已有帐号,去登陆*/
        tv_reg_gologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegActivity.this.finish();
            }
        });
        /** 换一张新的图片验证码*/
        tv_reg_get_new_yzm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //再次启动获取图片 异步任务
            }
        });
        /** 下一步 按钮*/
        btn_reg_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取内容
                username = et_reg_username.getText().toString();
                yzm = et_reg_yanzhengma.getText().toString();
                shoudaodeyzm = et_reg_shoudaodeyzm.getText().toString();
                //分别加以判断
                if(username!=null&&username.length()>6){
                    //判断yzm
                    if(yzm!=null&& yzm.length()== 4){
                        //判断收到的验证码
                        if(shoudaodeyzm!=null&&shoudaodeyzm.length()>4&&shoudaodeyzm.length()<9){
                            /** 通过格式检查*/
                            MyApplication inst = MyApplication.getInstance();
                            inst.getUser().setUsername(username).setPictureyzm(yzm).setYzm(shoudaodeyzm);
                            Intent intent = new Intent(RegActivity.this,Reg2Activity.class);
                            intent.putExtra("username",username);
                            startActivity(intent);
                        }else{
                            //收到的验证码格式不对
                        }
                    }else{
                        //4位验证码格式不对
                    }
                }else{
                    //用户名格式不对
                }
            }
        });
    }

    private void init() {
        layout_goback = ((RelativeLayout) findViewById(R.id.layout_goback));//返回按钮
        et_reg_username = ((EditText) findViewById(R.id.et_getpwd_username));//用户名
        et_reg_yanzhengma = ((EditText) findViewById(R.id.et_reg_yanzhengma));//图片验证码输入
        tv_reg_get_new_yzm = ((TextView) findViewById(R.id.tv_reg_huanyizhangyzm));//换一张图片验证码
        btn_reg_get_yzm = ((Button) findViewById(R.id.btn_getpwd_huoquyanzhengma));//获取手机/邮箱验证码
        et_reg_shoudaodeyzm = ((EditText) findViewById(R.id.et_getpwd_shoudaodeyzm));//收到的手机/邮箱验证码
        btn_reg_next = ((Button) findViewById(R.id.btn_getpwd_next));//下一步按钮
        tv_reg_gologin = ((TextView) findViewById(R.id.tv_reg_gologin));//已有账号,去登陆
    }
}
