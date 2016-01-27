package cn.com.yisquare.ibms.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import cn.com.yisquare.ibms.MyApplication;
import cn.com.yisquare.ibms.R;
import cn.com.yisquare.ibms.bean.User;
import cn.com.yisquare.ibms.bean.Worker;
import cn.com.yisquare.ibms.utils.DBHelper;

public class HomeActivity extends AppCompatActivity {

    protected TextView tv_home_publicfix_count;
    protected TextView tv_home_clientfix_count;
    protected TextView tv_home_routine_count;
    private long exitTime = 0;
    protected DBHelper dbHelper;
    protected Worker worker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initview();
        tv_home_clientfix_count.setVisibility(View.INVISIBLE);
        dbHelper = MyApplication.getInstance().getDbHelper();
        worker = dbHelper.getWorker("1");
        User user = new User();
        user.setId(1);
        user.setUsernumber(worker.getId());
        user.setProject(worker.getProject());//所在项目
        user.setDepartment(worker.getDepartment());//所在部门
        user.setGroup(worker.getGroup());//所在工作组
        user.setPost(worker.getPost());//职位
        user.setStatus(0);
        user.setName(worker.getName());
        MyApplication.getInstance().setUser(user);
        MyApplication.getInstance().setWorker(worker);

    }

    private void initview() {
        tv_home_publicfix_count = ((TextView) findViewById(R.id.tv_home_publicfix_count));
//        tv_home_clientfix_count = ((TextView) findViewById(R.id.tv_home_clientfix_count));
        tv_home_routine_count = ((TextView) findViewById(R.id.tv_home_routine_count));
    }
    /** 3个维修大类 圆圈 */
    public void onClickfix(View v){
        Intent intent = new Intent(this,Main2Activity.class);
        int classes = 0;
        switch (v.getId()){
            case R.id.ll_home_B_publicfix:
                classes = 1;
                break;
            case R.id.ll_home_B_clientfix:
                classes = 2;
                break;
            case R.id.ll_home_B_routine:
                classes = 1;
                break;
        }
        intent.putExtra("classes",classes);
        startActivity(intent);
    }
    /** 消息按钮 */
    public void onClickMsg(View v){
        Intent intent  =  new Intent(this,MsgActivity.class);
        startActivity(intent);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if((System.currentTimeMillis()-exitTime) > 2000){
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
