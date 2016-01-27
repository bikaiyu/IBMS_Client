package cn.com.yisquare.ibms.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import cn.com.yisquare.ibms.R;

public class GetPwdActivity2 extends Activity {

    protected RelativeLayout layout_getpwd2_goback;
    protected EditText et_getpwd2_pwd1;
    protected EditText et_getpwd2_pwd2;
    protected Button btn_getpwd2_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getpwd2);
        initview();
    }

    private void initview() {
        layout_getpwd2_goback = ((RelativeLayout) findViewById(R.id.layout_getpwd2_goback));
        layout_getpwd2_goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetPwdActivity2.this.finish();
            }
        });
        et_getpwd2_pwd1 = ((EditText) findViewById(R.id.et_getpwd2_pwd1));
        et_getpwd2_pwd2 = ((EditText) findViewById(R.id.et_getpwd2_pwd2));
        btn_getpwd2_next = ((Button) findViewById(R.id.btn_getpwd2_next));
        btn_getpwd2_next.setOnClickListener(new View.OnClickListener() {
            protected String pwd2;
            protected String pwd1;

            @Override
            public void onClick(View v) {
                pwd1 = et_getpwd2_pwd1.getText().toString();
                pwd2 = et_getpwd2_pwd2.getText().toString();
                if(pwd1!=null&&pwd2!=null&&pwd1.equals(pwd2)&&pwd1.length()>6&&pwd1.length()<17){
                    Toast.makeText(GetPwdActivity2.this, "修改成功", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(GetPwdActivity2.this,LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    GetPwdActivity2.this.startActivity(intent);
                }else{
                    Toast.makeText(GetPwdActivity2.this, "修改失败", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
