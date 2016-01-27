package cn.com.yisquare.ibms.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import cn.com.yisquare.ibms.R;

public class DialogActivity extends Activity {

    protected TextView tv_dialog_title;
    protected TextView tv_dialog_yes;
    protected TextView tv_dialog_no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_LEFT_ICON);
        setTitle("我是被设置的标题");// 设置标题
        setContentView(R.layout.activity_dialog);
        tv_dialog_title = ((TextView) findViewById(R.id.tv_dialog_title));
        tv_dialog_yes = ((TextView) findViewById(R.id.positiveButton));
        tv_dialog_no = ((TextView) findViewById(R.id.negativeButton));
        setFinishOnTouchOutside(false);
        Intent intent = getIntent();
        if(intent!=null){
            String dialog_title = intent.getStringExtra("dialog_title");
            String dialog_yes = intent.getStringExtra("dialog_yes");
            String dialog_no = intent.getStringExtra("dialog_no");
            if(isNull(dialog_title)||isNull(dialog_yes)||isNull(dialog_no))
                return;
            tv_dialog_title.setText(dialog_title);
            tv_dialog_yes.setText(dialog_yes);
            tv_dialog_no.setText(dialog_no);
        }
        tv_dialog_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(1);
                DialogActivity.this.finish();
            }
        });
        tv_dialog_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(0);
                DialogActivity.this.finish();
            }
        });

    }
    public void goAccept(View view){
        int result = 0;
        switch (view.getId()){
            case R.id.positiveButton:
//                Toast.makeText(this,"YES",Toast.LENGTH_SHORT).show();
                result = 1;
                break;
            case R.id.negativeButton:
//                Toast.makeText(this,"NO",Toast.LENGTH_SHORT).show();
                break;
        }
        setResult(result);
        DialogActivity.this.finish();
    }
    public boolean isNull(String temp){
        if(temp == null || temp.isEmpty())
            return true;
        else
            return false;
    }
}
